package com.project.freeq.repo;

import com.project.freeq.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    List<Ticket> getAllByQueueIdAndIsActiveOrderByPriorityDesc(Long queueId, Boolean isActive);
    List<Ticket> getAllByQueueIdAndIsActiveAndIsReadyOrderByPriorityDesc(Long queueId, Boolean isActive, Boolean isReady);
    Long countDistinctByQueueIdAndIsActive(Long queueId, Boolean isActive);
}
