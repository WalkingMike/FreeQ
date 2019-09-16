package com.project.freeq.service;

import com.project.freeq.model.Ticket;
import com.project.freeq.repo.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        newTicket.setQueueId(queue_id);
        newTicket.setUserId(user_id);
        saveTicket(newTicket);
    }

    public Ticket getOneById(Long id){
        Ticket ticket = ticketRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return ticket;
    }

    public List<Ticket> getAllActiveByQueueId(Long id, Boolean isActive){
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActive(id, isActive);
        return tickets;
    }

    public void saveTicket(Ticket ticket){
        ticketRepo.save(ticket);
    }

    public void removeTicket(Long id){
        ticketRepo.deleteById(id);
    }
}
