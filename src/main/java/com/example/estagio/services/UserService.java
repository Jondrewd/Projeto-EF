package com.example.estagio.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.estagio.domain.User;
import com.example.estagio.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }

    public User insert(User obj) {
        return userRepository.save(obj);
    }

    public User update(Integer id, User obj) {
        Optional<User> newObj = userRepository.findById(id);
        User user = newObj.get();
        updateUser(user, obj);
        return userRepository.save(user);
    }

    public void updateUser(User user, User obj) {
        user.setUsername(obj.getUsername());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Não foi possível achar esse nome.");
        }
    }
}
