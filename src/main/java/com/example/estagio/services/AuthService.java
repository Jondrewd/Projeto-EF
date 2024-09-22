package com.example.estagio.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.estagio.config.SecurityConfig;
import com.example.estagio.domain.Permission;
import com.example.estagio.domain.User;
import com.example.estagio.dto.AccountCredentialsDTO;
import com.example.estagio.dto.TokenDTO;
import com.example.estagio.repository.PermissionRepository;
import com.example.estagio.repository.UserRepository;
import com.example.estagio.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private SecurityConfig config;

    @SuppressWarnings("rawtypes")
    public ResponseEntity register(AccountCredentialsDTO data){
        try {
            User user = new User();
            user.setUsername(data.getUsername());
            user.setPassowrd(config.passwordEncoder().encode(data.getPassword()));

            Permission permissions = permissionRepository.findByName("USER").get();
            user.setPermission(Collections.singletonList(permissions));

            repository.save(user);
            return new ResponseEntity<>("Usuario registrado", HttpStatus.OK);
        } catch (Exception e) {
            throw new BadCredentialsException("Erro ao registrar-se.");
        }
    }
    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO data){
        try {
            var user = repository.findByUsername(data.getUsername());
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(auth);
            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.createAcessToken(data.getUsername(), user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username não encontrado.");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Username/password invalidos.");
        }
    }
    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String username, String refreshToken){
            var user = repository.findByUsername(username);

            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.refreshToken(refreshToken);
            }else{
                throw new UsernameNotFoundException("Username não encontrado.");
            }
            return ResponseEntity.ok(tokenResponse);
    }
}
