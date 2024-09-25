package site.dadangsinhhoc.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import site.dadangsinhhoc.models.UserModel;
import io.jsonwebtoken.io.Decoders;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.signerKey}")
    private String signerKey;

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

}
