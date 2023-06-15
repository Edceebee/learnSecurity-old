package com.example.learnSecurity.service;

import com.example.learnSecurity.models.AppRole;
import com.example.learnSecurity.models.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    AppUser saveAppUser(AppUser appUser);
    AppRole saveAppRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser getAppUser(String userName);
    List<AppUser> getAllUsers();
}
