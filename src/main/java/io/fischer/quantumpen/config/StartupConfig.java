package io.fischer.quantumpen.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class StartupConfig {

    private static final Logger logger = Logger.getLogger(StartupConfig.class.getName());

    @Bean
    CommandLineRunner startMessage() {
        return args -> {
            logger.info("API QuantumPen iniciada com sucesso!");
            logger.info("Swagger: http://localhost:8080/");
        };
    }
}