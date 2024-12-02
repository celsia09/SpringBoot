package com.example.revisaospring;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
@OpenAPIDefinition(
        info = @Info(
                title = " Projecto de Revisao Spring",
                description = "Api de treinamento",
                version = "1"
        )
)
@SecurityScheme(
        name = "jwt_auth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class RevisaoSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevisaoSpringApplication.class, args);
    }

}
