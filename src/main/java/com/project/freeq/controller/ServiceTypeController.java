package com.project.freeq.controller;


import com.project.freeq.model.Service;
import com.project.freeq.model.ServiceType;
import com.project.freeq.service.ServiceTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/servicetype")
public class ServiceTypeController {
    @Autowired
    private final ServiceTypeService serviceTypeService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<ServiceType> selectAll() {
        return serviceTypeService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/add")
    public void addService(@RequestBody ServiceType serviceType) {
        serviceTypeService.saveServiceType(serviceType);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @DeleteMapping(value = "/remove")
    public void removeService(@RequestParam Long id) {
        serviceTypeService.removeServiceType(id);
    }
}
