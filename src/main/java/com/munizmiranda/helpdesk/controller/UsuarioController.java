package com.munizmiranda.helpdesk.controller;

import com.munizmiranda.helpdesk.dto.UsuarioRequestDTO;
import com.munizmiranda.helpdesk.dto.UsuarioResponseDTO;
import com.munizmiranda.helpdesk.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.status(201).body(usuarioService.cadastrar(dto));
    }
}
