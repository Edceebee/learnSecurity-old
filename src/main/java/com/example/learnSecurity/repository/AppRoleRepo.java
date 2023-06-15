package com.example.learnSecurity.repository;

import com.example.learnSecurity.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRoleRepo extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
