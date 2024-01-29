package com.umc.bobmate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "밥친구 API 문서",
                description = "밥친구의 API 문서 입니다.",
                version = "v1",
                license = @License(
                        url = "https://github.com/Bob-Mate/BobMate-Server",
                        name = "밥친구"
                )
        )
)

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {

        // Security 스키마 설정
        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("Authorization")
                .in(SecurityScheme.In.HEADER)
                .name(HttpHeaders.AUTHORIZATION);

        // Security 요청 설정
        io.swagger.v3.oas.models.security.SecurityRequirement addSecurityItem = new io.swagger.v3.oas.models.security.SecurityRequirement().addList("Authorization");


        return new OpenAPI()
                // Security 인증 컴포넌트 설정
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                // API 마다 Security 인증 컴포넌트 설정
                .addSecurityItem(addSecurityItem);
    }

}