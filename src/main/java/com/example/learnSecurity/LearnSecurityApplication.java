package com.example.learnSecurity;

import com.example.learnSecurity.models.AppRole;
import com.example.learnSecurity.models.AppUser;
import com.example.learnSecurity.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class LearnSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnSecurityApplication.class, args);
	}
}
