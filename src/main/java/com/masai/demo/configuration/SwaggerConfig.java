package com.masai.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKeys(){

        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");

    }

    private List<SecurityReference> securityReferences(){

        AuthorizationScope scopes = new AuthorizationScope("Global","accessEverything");

        return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{scopes}));

    }

    private List<SecurityContext> securityContexts(){

        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());

    }

    @Bean
    public Docket api(){


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {
        return new ApiInfo("Ecommerce Backend Application : Rest APIs",
                "This project is Developed by Abhishek Bhandari","1.0",
                "Terms of Service",
                new Contact("Abhishek Bhandari", "https://abhi-shek-bhandari.github.io/",
                        "abhishekbhandarimail@gmail.com"),
                "Licence of Rest APIs","APIs Licence URL ", Collections.emptyList());
    }

}
