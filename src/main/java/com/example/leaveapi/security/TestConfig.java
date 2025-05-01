package com.example.leaveapi.security;

import com.example.leaveapi.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
	
	@Autowired
	private DatabaseService databaseService;

	@Bean
	public CommandLineRunner initializeDatabase(DatabaseService databaseService) {
		return args -> {
			databaseService.initializeDatabase();
		};
	}

}
