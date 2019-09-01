package com.project.freeq.repo;

import com.project.freeq.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
}
