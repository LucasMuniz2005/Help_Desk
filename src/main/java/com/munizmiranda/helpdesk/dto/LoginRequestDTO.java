package com.munizmiranda.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
    @NotBlank String email,
    @NotBlank String senha
) {}