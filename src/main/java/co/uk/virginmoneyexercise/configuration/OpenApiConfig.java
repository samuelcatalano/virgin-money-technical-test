package co.uk.virginmoneyexercise.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Scope;

@Configuration
public class OpenApiConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components())
                            .info(new Info()
                            .title("Virgin Money Application API")
                            .description("Spring Boot RESTful service using OpenAPI 3."));
    }
}