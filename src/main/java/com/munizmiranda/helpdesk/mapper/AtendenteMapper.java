package com.munizmiranda.helpdesk.mapper;

import com.munizmiranda.helpdesk.dto.AtendenteRequestDTO;
import com.munizmiranda.helpdesk.dto.AtendenteResponseDTO;
import com.munizmiranda.helpdesk.model.Atendente;
import org.springframework.stereotype.Component;

@Component
public class AtendenteMapper {

    public Atendente toEntity(AtendenteRequestDTO dto) {
        Atendente atendente = new Atendente();
        atendente.setNome(dto.nome());
        atendente.setEmail(dto.email());
        atendente.setSenha(dto.senha());
        atendente.setEspecialidade(dto.especialidade());
        return atendente;
    }

    public AtendenteResponseDTO toResponseDTO(Atendente atendente) {
        return new AtendenteResponseDTO(
                atendente.getId(),
                atendente.getNome(),
                atendente.getEmail(),
                atendente.getEspecialidade()
        );
    }
}