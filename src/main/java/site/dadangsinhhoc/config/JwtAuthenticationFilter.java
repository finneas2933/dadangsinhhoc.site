package site.dadangsinhhoc.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.services.TokenService;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
// Kiểm tra và xác thực JWT
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            log.info("JWT token found in request");
            try {
                UserModel user = tokenService.validateAndGetUserFromToken(jwt);
                if (user != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user.getUserName(), null, Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("User authenticated successfully: {}", user.getUserName());
                }

            } catch (Exception e) {
                log.error("Error validating JWT token", e);
                throw new RuntimeException(e);
            }
        }
        filterChain.doFilter(request, response);
    }
}