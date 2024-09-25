package site.dadangsinhhoc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import site.dadangsinhhoc.repositories.UserRepository;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    @Value("${jwt.signerKey}")
    private String signerKey;
//    private final ITokenService tokenService;
    private final JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Configuring security filter chain");
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(tokenService);

// TODO: Main
//        httpSecurity.cors(Customizer.withDefaults())
//                        .authorizeHttpRequests(requests -> requests
//                        .requestMatchers(HttpMethod.POST, "/api/users/validateToken", "/api/users/authenticate/**").permitAll()
//                        .requestMatchers("/swagger-ui/**", "/login", "/api/users/login").permitAll()
//                        .requestMatchers("/", "/index.html", "/static/**", "/js/**", "/css/**", "/img/**", "/favicon.ico").permitAll()
//                        .requestMatchers(HttpMethod.GET).permitAll()
//                        .requestMatchers(HttpMethod.POST).hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .successHandler(jwtAuthenticationSuccessHandler)
//                        .permitAll());
//        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(jwt -> jwt
//                        .decoder(securityConfig.jwtDecoder())
//                        .jwtAuthenticationConverter(token -> {
//                            JwtAuthenticationConverter converter = securityConfig.jwtAuthenticationConverter();
//                            AbstractAuthenticationToken auth = converter.convert(token);
//                            log.info("Converted token: {}", auth);
//                            return auth;
//                        })
//                )
//        );
//        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        log.info("Security filter chain configured successfully!");

// TODO: Test
        httpSecurity.cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.DELETE).permitAll()
                        .requestMatchers(HttpMethod.POST).permitAll()
                        .requestMatchers(HttpMethod.PUT).permitAll()
                        .requestMatchers("/swagger-ui/**", "/login", "/api/users/login").permitAll()
                        .requestMatchers("/", "/index.html", "/static/**", "/js/**", "/css/**", "/img/**", "/favicon.ico").permitAll());


        return httpSecurity.build();
    }
}
