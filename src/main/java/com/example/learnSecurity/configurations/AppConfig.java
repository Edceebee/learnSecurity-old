package com.example.learnSecurity.configurations;

import com.example.learnSecurity.models.AppRole;
import com.example.learnSecurity.models.AppUser;
import com.example.learnSecurity.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@Configuration
public class AppConfig {

    @Bean
    CommandLineRunner runner(AppUserService userService) {
        return args -> {
            userService.saveAppUser(new AppUser(null, "John Travolta", "john", "johnpass", new ArrayList<>()));
            userService.saveAppUser(new AppUser(null, "Will Smith", "will", "willpass", new ArrayList<>()));
            userService.saveAppUser(new AppUser(null, "Jim Carry", "jim", "jimpass", new ArrayList<>()));
            userService.saveAppUser(new AppUser(null, "Arnold Schwarzenegger", "arnold", "arnoldpass", new ArrayList<>()));

            userService.saveAppRole(new AppRole(null, "ROLE_USER"));
            userService.saveAppRole(new AppRole(null, "ROLE_MANAGER"));
            userService.saveAppRole(new AppRole(null, "ROLE_ADMIN"));
            userService.saveAppRole(new AppRole(null, "ROLE_SUPER_ADMIN"));

            userService.addRoleToUser("john", "ROLE_USER");
            userService.addRoleToUser("john", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_MANAGER");
            userService.addRoleToUser("will", "ROLE_USER");
            userService.addRoleToUser("jim", "ROLE_ADMIN");
            userService.addRoleToUser("jim", "ROLE_USER");
            userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_ADMIN");
            userService.addRoleToUser("arnold", "ROLE_USER");

        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
