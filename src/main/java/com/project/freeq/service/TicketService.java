package com.project.freeq.service;

import com.project.freeq.model.Ticket;
import com.project.freeq.repo.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ticketService")
@AllArgsConstructor
public class TicketService {

    @Autowired
    private final TicketRepo ticketRepo;

    public void saveTicket(Ticket ticket){
        ticketRepo.save(ticket);
    }

    public void removeTicket(Long id){
        ticketRepo.deleteById(id);
    }
}
