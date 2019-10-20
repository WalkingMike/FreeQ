package com.project.freeq.controller;


import com.project.freeq.model.Service;
import com.project.freeq.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private final ServiceService serviceService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<Service> selectAll() {
        return serviceService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/add")
    public void addService(@RequestBody Service service) {
        serviceService.saveService(service);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/modify")
    public void modifyService(@RequestBody Service service) {
        serviceService.modifyService(service);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @DeleteMapping(value = "/remove")
    public void removeService(@RequestParam Long id) {
        serviceService.removeService(id);
    }
}
