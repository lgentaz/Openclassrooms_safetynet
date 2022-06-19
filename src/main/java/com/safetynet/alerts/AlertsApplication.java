package com.safetynet.alerts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.JsonDB;
import com.safetynet.alerts.service.JsonDBService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@Configuration
@EntityScan("com.safetynet.alerts.model")
@EnableJpaRepositories(basePackages = "com.safetynet.alerts.repository")
public class AlertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlertsApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(JsonDBService jsonDBService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<JsonDB> typeReference = new TypeReference<JsonDB>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
            try {
                JsonDB jsonDB = mapper.readValue(inputStream,typeReference);
                jsonDBService.save(jsonDB);
                System.out.println("jsonDB Saved!");
            } catch (IOException e){
                System.out.println("Unable to save jsonDB: " + e.getMessage());
            }
        };
    }

}
