package com.munizmiranda.helpdesk.controller;

import com.munizmiranda.helpdesk.dto.AtendenteRequestDTO;
import com.munizmiranda.helpdesk.dto.AtendenteResponseDTO;
import com.munizmiranda.helpdesk.service.AtendenteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendentes")
public class AtendenteController {

    private final AtendenteService atendenteService;

    public AtendenteController(AtendenteService atendenteService) {
        this.atendenteService = atendenteService;
    }

    @PostMapping
    public ResponseEntity<AtendenteResponseDTO> cadastrar(@Valid @RequestBody AtendenteRequestDTO dto) {
        return ResponseEntity.status(201).body(atendenteService.cadastrar(dto));
    }
}