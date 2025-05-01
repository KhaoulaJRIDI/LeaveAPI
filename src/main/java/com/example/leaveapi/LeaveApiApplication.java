package com.example.leaveapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.leaveapi.repository")
@EntityScan(basePackages = "com.example.leaveapi.entity")
public class LeaveApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaveApiApplication.class, args);
    }

}
