package com.project.freeq.service;

import com.project.freeq.model.Ticket;
import com.project.freeq.repo.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketService")
@AllArgsConstructor
public class TicketService {
    @Autowired
    private final TicketRepo ticketRepo;

    public List<Ticket> getAll(){
        return ticketRepo.findAll();
    }

    public void createTicket(Long user_id, Long queue_id) {
        Ticket newTicket = new Ticket();
        newTicket.setIsActive(true);
        newTicket.setIsReady(true);
        newTicket.setQueueID(queue_id);
        newTicket.setUserID(user_id);
        saveTicket(newTicket);
    }

    public void saveTicket(Ticket ticket){
        ticketRepo.save(ticket);
    }

    public void removeTicket(Long id){
        ticketRepo.deleteById(id);
    }
}
