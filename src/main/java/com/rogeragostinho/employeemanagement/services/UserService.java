package com.rogeragostinho.employeemanagement.services;

import com.rogeragostinho.employeemanagement.dto.RegisterRequest;
import com.rogeragostinho.employeemanagement.entity.User; //
import com.rogeragostinho.employeemanagement.repository.UserRepository; //
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Erro: Este nome de utilizador já está em uso!");
        }

        User user = new User();
        user.setUsername(request.getUsername());

        String passwordEncriptada = passwordEncoder.encode(request.getPassword());
        user.setPassword(passwordEncriptada);

        // Se tiveres Roles/Perfil (ex: ROLE_USER), define-as aqui também:
        // user.setRole("ROLE_USER");

        return userRepository.save(user);
    }
}