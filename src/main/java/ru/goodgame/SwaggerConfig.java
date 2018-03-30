package ru.goodgame;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
    @Bean
    public Docket monitorApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.goodgame.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }


    private ApiInfo metaData() {
        return new ApiInfo(
                "Game synchronization module with REST API",
                "Get synchronization data from users and provide access to user data",
                "0.1",
                "Terms of Service",
                new Contact("Sergey Vlasov", "", "madk1nd86@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
    }
}
