package com.umc.bobmate.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

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
    /**
     * OpenAPI bean 구성
     * @return
     */
}