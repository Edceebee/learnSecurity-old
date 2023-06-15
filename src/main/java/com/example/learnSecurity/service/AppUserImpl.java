package com.example.learnSecurity.service;

import com.example.learnSecurity.models.AppRole;
import com.example.learnSecurity.models.AppUser;
import com.example.learnSecurity.repository.AppRoleRepo;
import com.example.learnSecurity.repository.AppUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AppUserImpl implements AppUserService, UserDetailsService {

    @Autowired
    AppUserRepo appUserRepo;

    private final AppRoleRepo appRoleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepo.findByUserName(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found");
        }
        log.info("User found in the --> {}", user);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getUserName(), user.getPassword(), authorities);
    }

    @Override
    public AppUser saveAppUser(AppUser appUser) {
        log.info("Saving new {} user to the database", appUser.getName());
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return appUserRepo.save(appUser);
    }

    @Override
    public AppRole saveAppRole(AppRole appRole) {
        log.info("Saving new role {} to the database", appRole.getName());
        return appRoleRepo.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName, username);
        AppUser user = appUserRepo.findByUserName(username);
        AppRole role = appRoleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getAppUser(String userName) {
        log.info("fetching user {}", userName);
        return appUserRepo.findByUserName(userName);
    }

    @Override
    public List<AppUser> getAllUsers() {
        log.info("fetching all users ");
        return appUserRepo.findAll();
    }

}
