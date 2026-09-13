package com.munizmiranda.helpdesk.dto;

import com.munizmiranda.helpdesk.model.Categoria;
import com.munizmiranda.helpdesk.model.Prioridade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketRequestDTO(
    @NotBlank(message = "O título é obrigatório")
    String titulo,

    @NotBlank(message = "A descrição é obrigatória")
    String descricao,

    @NotNull(message = "A categoria é obrigatória")
    Categoria categoria,

    @NotNull(message = "A prioridade é obrigatória")
    Prioridade prioridade
) {}