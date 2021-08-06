package pl.damian.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Import(BeanValidatorPluginsConfiguration.class)
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Collections.singletonList(swaggerSecurityContext()))
                .securitySchemes(Collections.singletonList(new ApiKey("JWT", "Authorization", "header")))
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.damian.shop.controller"))
                .build();

    }

    private SecurityContext swaggerSecurityContext() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("JWT", authorizationScopes)))
                .build();
    }

}
