package com.munizmiranda.helpdesk.dto;

import com.munizmiranda.helpdesk.model.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtendenteRequestDTO(
    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    String email,

    @NotBlank(message = "A senha é obrigatória")
    String senha,

    @NotNull(message = "A especialidade é obrigatória")
    Especialidade especialidade
) {}