package com.springboot.blog.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder().group("TestDemo").pathsToMatch("/api/**").build();

    }
}
