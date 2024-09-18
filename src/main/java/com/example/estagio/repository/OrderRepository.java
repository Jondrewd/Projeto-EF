package com.example.estagio.repository;

import com.example.estagio.domain.Order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String>{
    
}
