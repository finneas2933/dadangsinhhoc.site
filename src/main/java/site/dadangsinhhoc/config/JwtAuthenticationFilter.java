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
import site.dadangsinhhoc.services.ITokenService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
// Kiểm tra và xác thực JWT
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ITokenService tokenService;

    @Autowired
    public JwtAuthenticationFilter(ITokenService tokenService) {
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
                    List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user.getEmail(), null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("User authenticated successfully: {} with role: {}", user.getEmail(), user.getRole());
                }

            } catch (Exception e) {
                log.error("Error validating JWT token", e);
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}