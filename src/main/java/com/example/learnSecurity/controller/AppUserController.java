package com.example.learnSecurity.controller;

import com.example.learnSecurity.models.AppRole;
import com.example.learnSecurity.models.AppUser;
import com.example.learnSecurity.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @PostMapping("/appUser/save")
    public ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/appUser/save").toUriString());
        return  ResponseEntity.created(uri).body(appUserService.saveAppUser(appUser));
    }

    @PostMapping("/appRole/save")
    public ResponseEntity<AppRole> saveRole(@RequestBody AppRole appRole) {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/appRole/save").toUriString());
        return  ResponseEntity.created(uri).body(appUserService.saveAppRole(appRole));
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getUsers () {
        return ResponseEntity.ok().body(appUserService.getAllUsers());

    }

    @PostMapping("/addRole/{userName}/{roleName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable String userName, String roleName ) {
        appUserService.addRoleToUser(userName, roleName);
        return ResponseEntity.ok().build();

    }


}
