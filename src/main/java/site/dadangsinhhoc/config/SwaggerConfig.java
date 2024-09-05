package site.dadangsinhhoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.dadangsinhhoc.exception.SwaggerInitializationException;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);
    @Bean
    public Docket api() {
        try {
            Docket docket = new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("site.dadangsinhhoc"))
                    .paths(PathSelectors.regex("/.*"))
                    .build().apiInfo(apiInfo());
            logger.info("Start project with: http://localhost:8080/login");
            logger.info("Swagger UI in: http://localhost:8080/swagger-ui/index.html#/");
            logger.info("Client in: http://localhost:3000");

//            logger.info("Phpmyadmin in: http://localhost/phpmyadmin/index.php?route=/database/structure&db=dongthucvat   ");

            return docket;
        } catch (Exception e) {
            throw new SwaggerInitializationException("Failed to initialize Swagger documentation", e);
        }
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Geeks API")
                .description("Demonstration of all write portal API")
                .version("1.0")
                .build();
    }
}