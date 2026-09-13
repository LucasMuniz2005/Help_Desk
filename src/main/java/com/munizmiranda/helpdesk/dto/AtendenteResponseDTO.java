package com.munizmiranda.helpdesk.dto;

import com.munizmiranda.helpdesk.model.Especialidade;

public record AtendenteResponseDTO(
    Long id,
    String nome,
    String email,
    Especialidade especialidade
) {}
