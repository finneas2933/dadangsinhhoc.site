package site.dadangsinhhoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.dadangsinhhoc.exception.SwaggerInitializationException;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        try {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("site.dadangsinhhoc"))
                    .paths(PathSelectors.regex("/.*"))
                    .build().apiInfo(apiInfo());
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