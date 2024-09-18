package com.example.estagio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.estagio.services.UserService;


@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService service;

}
