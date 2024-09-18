package com.example.estagio.repository;

import com.example.estagio.domain.License;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<License, String>{
    
}
