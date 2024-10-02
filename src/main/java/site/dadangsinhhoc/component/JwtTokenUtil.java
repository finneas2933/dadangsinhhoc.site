package site.dadangsinhhoc.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import site.dadangsinhhoc.models.TokenModel;
import site.dadangsinhhoc.models.UserModel;
import io.jsonwebtoken.io.Decoders;
import site.dadangsinhhoc.repositories.TokenRepository;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class JwtTokenUtil {

    private final TokenRepository tokenRepository;

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.signerKey}")
    private String signerKey;
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
    public String generateToken(UserModel userModel) {
        log.info("Starting token generation for user: {}", userModel.getEmail());

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", userModel.getEmail());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userModel.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
            log.info("Create JWT Token success, key: " + token);
            return token;
        }
        catch (Exception e) {
            log.error("Can't create JWT token, detail error:" + e.getMessage());
            return null;
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(signerKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims  = this.extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    public boolean isTokenExprired(String token) {
        Date exDate = this.extractClaims(token, Claims::getExpiration);
        return exDate.before(new Date());
    }

//    public String generateSercetKey(){
//        SecureRandom random = new SecureRandom();
//        byte[] keyBytes = new byte[32];
//        random.nextBytes(keyBytes);
//        String secretKey = Encoders.BASE64.encode(keyBytes);
//        return secretKey;
//    }

    public String getEmailFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserModel userModel) {
        try {
            String email = getEmailFromToken(token);
            TokenModel existingToken = tokenRepository.findByToken(token);
            if (existingToken == null || existingToken.isRevoked() == true || !userModel.getStatus()) {
                return false;
            }
            return (email.equals(userModel.getEmail())) && !isTokenExprired(token);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
