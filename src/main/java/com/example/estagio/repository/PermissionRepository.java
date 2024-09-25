package com.example.estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estagio.domain.Permission;
import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
    
    Optional<Permission> findByName(String name);
}
