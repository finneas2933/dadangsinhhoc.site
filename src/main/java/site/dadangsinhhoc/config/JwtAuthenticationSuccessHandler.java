package site.dadangsinhhoc.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import site.dadangsinhhoc.models.UserModel;
import site.dadangsinhhoc.repositories.UserRepository;
import site.dadangsinhhoc.services.TokenService;

import java.io.IOException;

@Component
@Slf4j
// Token được tạo ra sau khi đăng nhập                                                                                          thành công
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationSuccessHandler(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        log.info("Authentication successful for user: {}", authentication.getName());
        String username = authentication.getName();
        UserModel user = userRepository.findByUserName(username);
        log.info("Generating token for user: {}", username);

        String token = tokenService.generateToken(user);
        log.info("Token generated successfully");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"token\":\"" + token + "\"}");
        log.info("Token sent to client");

        response.sendRedirect("/swagger-ui/index.html");
        log.info("Redirected to Swagger UI");
    }
}