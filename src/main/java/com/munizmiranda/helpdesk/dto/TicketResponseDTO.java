package com.munizmiranda.helpdesk.dto;

import com.munizmiranda.helpdesk.model.*;

import java.time.LocalDateTime;

public record TicketResponseDTO(
    Long id,
    String titulo,
    String descricao,
    Categoria categoria,
    Prioridade prioridade,
    Status status,
    Sentimento sentimento,
    LocalDateTime dataAbertura,
    LocalDateTime dataFechamento,
    UsuarioResumoDTO usuario,
    AtendenteResumoDTO atendente
) {}