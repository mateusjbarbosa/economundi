package com.backend.economundi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    final private String PATH_PACKAGE = "com.backend.economundi.controller";
    final private String TITLE = "EconoMundi API";
    final private String DESCRIPTION = "Portal agregador do mundo econômico!";
    final private String VERSION = "1.0.0";

    @Value("${swagger.enabled}")
    private boolean swaggerEnabled;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(PATH_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getAppInfo())
                .enable(swaggerEnabled);
    }

    private ApiInfo getAppInfo() {
        return new ApiInfoBuilder().title(TITLE)
                .description(DESCRIPTION)
                .version(VERSION)
                .build();
    }
}
