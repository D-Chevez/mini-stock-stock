package org.kodigo.mini_stock_sytem.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("MINI STOCK SYSTEM - API")
                        .description("API para la gestión de un sistema de inventario simple")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Diego Chevez")
                                .url("https://github.com/D-Chevez")
                        )
                        .contact(new Contact()
                                .name("Nicole Sanchez")
                                .url("https://github.com/nicolenohemysanchez")
                        )
                        .contact(new Contact()
                                .name("Roberto Mendez")
                                .url("https://github.com/romendezs")
                        )
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo")

                ));
    }
}
