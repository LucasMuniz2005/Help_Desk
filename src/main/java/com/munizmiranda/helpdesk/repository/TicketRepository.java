package com.munizmiranda.helpdesk.repository;

import com.munizmiranda.helpdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}