package com.project.freeq.controller;


import com.project.freeq.model.Queue;
import com.project.freeq.service.QueueService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/queue")
public class QueueController {
    @Autowired
    private final QueueService queueService;

    @GetMapping(value = "/getall")
    public @ResponseBody List<Queue> selectAll() {
        return queueService.getAll();
    }

    @GetMapping(value = "/count")
    public @ResponseBody Long count(@RequestParam Long id) {
        return queueService.countTickets(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/advance")
    public void addQueue(@RequestParam Long id) {
        queueService.advanceQueue(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/add")
    public void addQueue(@RequestBody Queue queue) {
        queueService.saveQueue(queue);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @DeleteMapping(value = "/remove")
    public void removeQueue(@RequestParam Long id) {
        queueService.removeQueue(id);
    }
}
