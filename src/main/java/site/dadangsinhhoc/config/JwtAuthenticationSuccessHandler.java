package site.dadangsinhhoc.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import site.dadangsinhhoc.component.JwtTokenUtil;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.UserRepository;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("Authentication successful for user: {}", authentication.getName());
        String email = authentication.getName();
        UserModel user = userRepository.findByEmail(email);
        log.info("Generating token for user: {}", email);

//        String token = jwtTokenUtil.generateToken(user);
//        log.info("Token generated successfully");

        // response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        // response.getWriter().write("{\"token\":\"" + token + "\"}");
        // log.info("Token sent to client");

        response.sendRedirect("/swagger-ui/index.html");
        log.info("Redirected to Swagger UI");
    }
}