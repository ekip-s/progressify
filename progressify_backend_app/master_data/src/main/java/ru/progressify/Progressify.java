package ru.progressify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.model.repository")
@EntityScan(basePackages = "ru.model.models")
public class Progressify {
    public static void main(String[] args) {
        SpringApplication.run(Progressify.class, args);
    }
}