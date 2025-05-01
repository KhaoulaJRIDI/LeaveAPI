package com.example.leaveapi.service;


import com.example.leaveapi.entity.User;
import com.example.leaveapi.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void  initializeDatabase() {


		System.out.println("Initializing database...");
		
		final User user1 = new User("Emma", "emma@mail.com", passwordEncoder.encode("111"));
		final User user2 = new User("Jhon", "jhon@mail.com", passwordEncoder.encode("222"));
		final User admin = new User("Anna", "anna@mail.com", passwordEncoder.encode("333"));
		
		//user1.addRole(Role.MANAGER);
		admin.addRole(Role.ADMIN);
	
		System.out.println("USER 1 is a MANAGER: "+userService.create(user1));
		System.out.println(userService.create(user2));
		System.out.println(userService.create(admin));
		
		System.out.println("Database initialized!");
	}
}
