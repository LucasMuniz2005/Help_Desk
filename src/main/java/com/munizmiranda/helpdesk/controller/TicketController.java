package com.munizmiranda.helpdesk.controller;

import java.io.IOException;
import org.springframework.http.MediaType;
import com.munizmiranda.helpdesk.dto.TicketRequestDTO;
import com.munizmiranda.helpdesk.dto.TicketResponseDTO;
import com.munizmiranda.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
public ResponseEntity<TicketResponseDTO> criarTicket(@Valid @RequestBody TicketRequestDTO dto) {
    String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
    TicketResponseDTO novoTicket = ticketService.criarTicket(dto, emailUsuario);
    return ResponseEntity.status(201).body(novoTicket);
}

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> listarTickets() {
        return ResponseEntity.ok(ticketService.listarTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.buscarPorId(id));
    }

    @PatchMapping("/{id}/fechar")
    public ResponseEntity<TicketResponseDTO> fecharTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.fecharTicket(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTicket(@PathVariable Long id) {
        ticketService.deletarTicket(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/assumir")
public ResponseEntity<TicketResponseDTO> assumirTicket(@PathVariable Long id) {
    String emailAtendente = SecurityContextHolder.getContext().getAuthentication().getName();
    return ResponseEntity.ok(ticketService.assumirTicket(id, emailAtendente));
}
@GetMapping("/relatorio")
public ResponseEntity<byte[]> gerarRelatorio() throws IOException {
    String emailAtendente = SecurityContextHolder.getContext().getAuthentication().getName();
    byte[] excel = ticketService.gerarRelatorio(emailAtendente);

    return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=relatorio_tickets.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(excel);
}
}