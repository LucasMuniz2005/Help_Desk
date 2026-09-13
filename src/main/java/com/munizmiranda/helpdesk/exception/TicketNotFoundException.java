    package com.munizmiranda.helpdesk.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket não encontrado com o id: " + id);
    }
}