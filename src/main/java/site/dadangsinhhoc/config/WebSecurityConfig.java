package site.dadangsinhhoc.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import site.dadangsinhhoc.filters.JwtTokenFilter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        log.info("Configuring security filter chain");

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
        httpSecurity
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/users/login").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui/index.html#/").permitAll()
//                .requestMatchers("/v3/**").permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .requestMatchers(HttpMethod.DELETE).permitAll()
                .requestMatchers(HttpMethod.POST).permitAll()
                .requestMatchers(HttpMethod.PUT).permitAll()
                .requestMatchers("/", "/index.html", "/static/**", "/js/**", "/css/**", "/img/**", "/favicon.ico").permitAll())
                .cors(AbstractHttpConfigurer::disable);

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
