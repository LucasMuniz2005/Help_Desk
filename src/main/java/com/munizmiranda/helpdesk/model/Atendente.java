package com.munizmiranda.helpdesk.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "atendentes")
@Getter
@Setter
@NoArgsConstructor
public class Atendente extends PessoaBase {

    @NotNull(message = "A especialidade é obrigatória")
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
}