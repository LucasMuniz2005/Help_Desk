package com.munizmiranda.helpdesk.controller;

import com.munizmiranda.helpdesk.model.Ticket;
import com.munizmiranda.helpdesk.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> criarTicket(@RequestBody Ticket ticket) {
        Ticket novoTicket = ticketService.criarTicket(ticket);
        return ResponseEntity.status(201).body(novoTicket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> listarTickets() {
        return ResponseEntity.ok(ticketService.listarTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.buscarPorId(id));
    }

    @PatchMapping("/{id}/fechar")
    public ResponseEntity<Ticket> fecharTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.fecharTicket(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicket(@PathVariable Long id) {
        ticketService.deletarTicket(id);
        return ResponseEntity.noContent().build();
    }
}