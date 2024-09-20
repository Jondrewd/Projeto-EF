package com.example.estagio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.estagio.domain.Order;
import com.example.estagio.exceptions.ResourceNotFoundException;
import com.example.estagio.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> findAll(Pageable pageable){
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders;
    }
    public Order findById(String id){
        try{
        Optional<Order> obj = orderRepository.findById(id);
        return obj.get();
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Order insert(Order obj) {
        return orderRepository.save(obj);
    }

    public void delete(String id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
