package com.pfe.serviceabcense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceAbcenseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAbcenseApplication.class, args);
    }

}
