package com.example.estagio.repository;

import com.example.estagio.domain.Documents;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Documents, String>{
    
}
