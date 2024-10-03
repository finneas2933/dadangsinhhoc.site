package site.dadangsinhhoc.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import site.dadangsinhhoc.component.JwtTokenUtil;
import site.dadangsinhhoc.models.UserModel;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            if (isBypassToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "authHeader null or not started with Bearer");
                return;
            }

            final String token = authHeader.substring(7);
            final String email = jwtTokenUtil.getEmailFromToken(token);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserModel userDetail = (UserModel) userDetailsService.loadUserByUsername(email);
                if(jwtTokenUtil.validateToken(token, userDetail)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetail,
                            null,
                            userDetail.getAuthorities()
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        } catch (IOException | ServletException | UsernameNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }


    private boolean isBypassToken(HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = Arrays.asList(
                Pair.of("/api/**", "GET"),
                Pair.of("/api/users/login", "GET"),
                Pair.of("/login", "GET"),
                Pair.of("/swagger-ui/**", "GET"),
                Pair.of("/v3/**", "GET"),
                Pair.of("/index.html", "GET"),
                Pair.of("/static/**", "GET"),
                Pair.of("/img/**", "GET"),
                Pair.of("/static/favicon.ico", "GET")

        );
        String requestPath = request.getServletPath();;
        String requestMethod = request.getMethod();

        for (Pair<String, String> bypassToken : bypassTokens) {
            String path = bypassToken.getFirst();
            String method = bypassToken.getSecond();
            if (requestPath.matches(path.replace("**", ".*"))
                && requestMethod.equals(method)) {
                return true;
            }
        }
        return false;
    }
}
