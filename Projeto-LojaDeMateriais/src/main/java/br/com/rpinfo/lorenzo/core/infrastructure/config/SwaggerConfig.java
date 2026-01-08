package br.com.rpinfo.lorenzo.core.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Clientes",
                version = "1.0",
                description = "API para gerenciamento de clientes, fornecedores e operações"
        )
)
public class SwaggerConfig {

}
