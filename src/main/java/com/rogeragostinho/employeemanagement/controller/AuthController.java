package com.rogeragostinho.employeemanagement.controller;

import com.rogeragostinho.employeemanagement.dto.AuthResponse;
import com.rogeragostinho.employeemanagement.dto.LoginRequest;
import com.rogeragostinho.employeemanagement.dto.RegisterRequest;
import com.rogeragostinho.employeemanagement.entity.User;
import com.rogeragostinho.employeemanagement.services.JwtService;
import com.rogeragostinho.employeemanagement.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService service;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService service) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Se passou, extraímos os detalhes do utilizador autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User newUser = service.registerNewUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Utilizador registado com sucesso!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Limpa o utilizador atualmente autenticado no contexto do Spring Security
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logout efetuado com sucesso! Remova o token do cliente.");
    }
}
