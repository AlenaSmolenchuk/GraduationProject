package ru.mts.graduation_project.customservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomServiceApplication.class, args);
    }

}
