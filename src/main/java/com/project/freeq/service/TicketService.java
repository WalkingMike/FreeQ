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
        newTicket.setQueueId(queue_id);
        newTicket.setUserId(user_id);
        saveTicket(newTicket);
    }

    public Ticket getOneById(Long id){
        if (null != id) {
            Ticket ticket = ticketRepo.getOne(id);
            return ticket;
        }
        else return null;
    }

    public Long getCountTicketsInQueue(Long queueId, Boolean active){
        Long count = ticketRepo.countDistinctByQueueIdAndIsActive(queueId, active);
        return count;
    }

    public Ticket getNextTicket(Long queueId){
        Ticket ticket = ticketRepo.getAllByQueueIdAndIsActiveAndIsReadyOrderByPriorityDesc(queueId, true, true).get(0);
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

    public List<Ticket> getAllActiveByQueueId(Long id, Boolean isActive){
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActiveOrderByPriorityDesc(id, isActive);
        return tickets;
    }

    public void decrementTicketsPriorityInQueue(Long id) {
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActiveAndIsReadyOrderByPriorityDesc(id, true, false);
        for(Ticket ticket : tickets){
            if (ticket.getPriority() > 1.0){
                ticket.setPriority(ticket.getPriority() - (float)1);
            }
            ticketRepo.save(ticket);
        }
    }

    public void incrementTicketsPriorityInQueue(Long id) {
        List<Ticket> tickets = ticketRepo.getAllByQueueIdAndIsActiveOrderByPriorityDesc(id, true);
        for(Ticket ticket : tickets){
            ticket.setPriority(1 + ticket.getPriority());
            ticketRepo.save(ticket);
        }
    }

    public void modifyTicket(Ticket ticket){
        Ticket modTicket = ticketRepo.getOne(ticket.getId());
        if (null != modTicket) {
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
