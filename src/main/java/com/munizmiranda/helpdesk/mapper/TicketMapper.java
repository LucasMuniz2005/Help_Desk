package com.munizmiranda.helpdesk.mapper;

import com.munizmiranda.helpdesk.dto.AtendenteResumoDTO;
import com.munizmiranda.helpdesk.dto.TicketRequestDTO;
import com.munizmiranda.helpdesk.dto.TicketResponseDTO;
import com.munizmiranda.helpdesk.dto.UsuarioResumoDTO;
import com.munizmiranda.helpdesk.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public Ticket toEntity(TicketRequestDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setTitulo(dto.titulo());
        ticket.setDescricao(dto.descricao());
        ticket.setCategoria(dto.categoria());
        ticket.setPrioridade(dto.prioridade());
        return ticket;
    }

    public TicketResponseDTO toResponseDTO(Ticket ticket) {
        UsuarioResumoDTO usuarioResumo = new UsuarioResumoDTO(
                ticket.getUsuario().getId(),
                ticket.getUsuario().getNome()
        );

        AtendenteResumoDTO atendenteResumo = ticket.getAtendente() != null
                ? new AtendenteResumoDTO(ticket.getAtendente().getId(), ticket.getAtendente().getNome())
                : null;

        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitulo(),
                ticket.getDescricao(),
                ticket.getCategoria(),
                ticket.getPrioridade(),
                ticket.getStatus(),
                ticket.getSentimento(),
                ticket.getDataAbertura(),
                ticket.getDataFechamento(),
                usuarioResumo,
                atendenteResumo
        );
    }
}