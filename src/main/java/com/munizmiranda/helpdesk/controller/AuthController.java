package com.munizmiranda.helpdesk.controller;

import com.munizmiranda.helpdesk.dto.LoginRequestDTO;
import com.munizmiranda.helpdesk.dto.LoginResponseDTO;
import com.munizmiranda.helpdesk.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping("/login-atendente")
public ResponseEntity<LoginResponseDTO> loginAtendente(@Valid @RequestBody LoginRequestDTO dto) {
    return ResponseEntity.ok(authService.loginAtendente(dto));
}
}