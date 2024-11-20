package com.example.ecommerce.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Shopping Mall API")
                      .version("1.0.0")
                        .description("API documentation for the Shopping Mall application")
                );
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
               .group("shopping-mall-api")
                .pathsToMatch("/orders/**")
               .build();
    }


 
}
