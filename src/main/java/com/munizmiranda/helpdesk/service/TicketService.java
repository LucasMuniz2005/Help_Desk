package com.munizmiranda.helpdesk.service;

import com.munizmiranda.helpdesk.exception.TicketNotFoundException;
import com.munizmiranda.helpdesk.model.Status;
import com.munizmiranda.helpdesk.model.Ticket;
import com.munizmiranda.helpdesk.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket criarTicket(Ticket ticket) {
        ticket.setStatus(Status.ABERTO);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarTickets() {
        return ticketRepository.findAll();
    }

    public Ticket buscarPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    public Ticket fecharTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));

        ticket.setStatus(Status.FECHADO);
        ticket.setDataFechamento(LocalDateTime.now());

        return ticketRepository.save(ticket);
    }

    public void deletarTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        ticketRepository.deleteById(id);
    }
}