package com.project.freeq.controller;


import com.project.freeq.model.Ticket;
import com.project.freeq.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody
    List<Ticket> selectAll() {
        return ticketService.getAll();
    }

    @PostMapping(value = "/add")
    public void addTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
    }

    @DeleteMapping(value = "/remove")
    public void removeTicket(@RequestParam Long id) {
        ticketService.removeTicket(id);
    }
}
