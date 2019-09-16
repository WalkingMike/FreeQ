package com.project.freeq.repo;

import com.project.freeq.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> getAllByQueueIdAndIsActive(Long queueId, Boolean isActive);
}
