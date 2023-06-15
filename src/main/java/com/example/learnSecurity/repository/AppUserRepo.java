package com.example.learnSecurity.repository;

import com.example.learnSecurity.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUserName(String userName);
}
