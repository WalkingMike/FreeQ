package com.project.freeq.service;

import com.project.freeq.model.Queue;
import com.project.freeq.model.Ticket;
import com.project.freeq.repo.QueueRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service("queueService")
@AllArgsConstructor
public class QueueService {
    @Autowired
    private final QueueRepo queueRepo;

    @Autowired
    private final TicketService ticketService;

    @Autowired
    private final TicketService ticketRepo;

    @Transactional
    public void advanceQueue(Long queueId){
        endCurrentTicket(queueId);
        setNextTicketAsCurrent(queueId);
    }

    public void endCurrentTicket(Long queueId){
        Queue queue = queueRepo.findById(queueId).orElseThrow(EntityNotFoundException::new);
        Ticket ticket = ticketService.getOneById(queue.getCurrentTicketID());
        if (null != ticket) {
            ticket.setFinish(new Date());
            ticket.setIsActive(false);
            ticketRepo.saveTicket(ticket);
        }
        else return;
    }

    public Long countTickets(Long queueId){
        return ticketService.getCountTicketsInQueue(queueId, true);
    }

    public void setNextTicketAsCurrent(Long queueId){
        Long ticketId = ticketService.getNextTicket(queueId).getId();
        setCurrentTicket(queueId, ticketId);
        ticketService.decrementTicketsPriorityInQueue(queueId);
    }

    public void setCurrentTicket(Long queueId, Long ticketId){
        Queue queue = queueRepo.findById(queueId).orElseThrow(EntityNotFoundException::new);
        queue.setCurrentTicketID(ticketId);
        queueRepo.save(queue);
    }

    public void modifyQueue(Queue q){
        Queue modQueue = queueRepo.getOne(q.getId());
        if (null != modQueue) {
            queueRepo.save(q);
        }
    }
    
    public List<Queue> getAll(){
        return queueRepo.findAll();
    }

    public List<Queue> getAllByServiceID(Long id){
        return queueRepo.findAllByServiceID(id);
    }

    public void saveQueue(Queue queue){
        queueRepo.save(queue);
    }

    public void removeQueue(Long id){
        queueRepo.deleteById(id);
    }
}
