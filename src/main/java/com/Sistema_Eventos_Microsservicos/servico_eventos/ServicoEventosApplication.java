package com.Sistema_Eventos_Microsservicos.servico_eventos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Para se registrar no Eureka Server
public class ServicoEventosApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServicoEventosApplication.class, args);
    }
}
