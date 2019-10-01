package com.project.freeq.service;

import com.project.freeq.model.Ticket;
import com.project.freeq.repo.TicketRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
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

    public void setIsReady(Long id, Boolean ready){
        Ticket ticket = ticketRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (ticket.getIsActive()) {
            if (ready) {
                ticket.setIsReady(true);
            } else {
                if (ticket.getPriority() > 1.0){
                    ticket.setPriority(ticket.getPriority() - (float)0.5);
                }
                ticket.setIsReady(false);
            }
        }
    }

    public List<Ticket> getAllByQueueId(Long id){
        List<Ticket> tickets = ticketRepo.getAllByQueueIdOrderByPriorityDesc(id);
        return tickets;
    }

    public List<Ticket> getAllActiveByQueueId(Long id, Boolean isActive){
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActive(id, isActive);
        return tickets;
    }

    public void incrementTicketsPriorityInQueue(Long id) {
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActive(id, true);
        for(Ticket ticket : tickets){
            ticket.setPriority(1 + ticket.getPriority());
            ticketRepo.save(ticket);
        }
    }

    @Transactional
    public void saveTicket(Ticket ticket){
        Long queueId = ticket.getQueueId();
        incrementTicketsPriorityInQueue(queueId);
        ticket.setPriority((float)1);
        ticket.setIsActive(true);
        ticket.setIsReady(true);
        ticket.setStart(new Date());
        ticketRepo.save(ticket);
    }

    public void removeTicket(Long id){
        ticketRepo.deleteById(id);
    }
}
