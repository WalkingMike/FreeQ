package com.project.freeq.controller;


import com.project.freeq.config.security.UserPrincipalService;
import com.project.freeq.model.Ticket;
import com.project.freeq.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    @Autowired
    private final TicketService ticketService;

    @GetMapping(value = "/getall")
    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    public @ResponseBody List<Ticket> selectAll() {
        return ticketService.getAll();
    }

    @GetMapping(value = "/get/id")
    public @ResponseBody Ticket getOneById(@RequestParam Long id) {
        return ticketService.getOneById(id);
    }

    @GetMapping(value = "/get/queueidandisactive")
    public @ResponseBody List<Ticket> getAllActiveByQueueId(@RequestParam Long queueId, @RequestParam Boolean isActive) {
        return ticketService.getAllActiveByQueueId(queueId, isActive);
    }

    @GetMapping(value = "/get/queueid")
    public @ResponseBody List<Ticket> getAllByQueueId(@RequestParam Long queueId) {
        return ticketService.getAllActiveByQueueId(queueId, true);
    }

    @PostMapping(value = "/readiness")
    @PreAuthorize("hasRole('ADMIN') or UserPrincipalDetailsService.isSameWithTickets(principal, ticketId)")
    public void setReadiness(@RequestParam Long ticketId, @RequestParam Boolean isReady) {
        ticketService.setIsReady(ticketId, isReady);
    }

    @PostMapping(value = "/enroll")
    public void addQueue(@RequestParam Long user_id, @RequestParam Long queue_id) {
        ticketService.createTicket(user_id, queue_id);
    }

    @PostMapping(value = "/add")
    public void addTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
    }

    @DeleteMapping(value = "/remove")
    @PreAuthorize("hasRole('ADMIN') or UserPrincipalDetailsService.isSameWithTickets(principal, id)")
    public void removeTicket(@RequestParam Long id) {
        ticketService.removeTicket(id);
    }
}
