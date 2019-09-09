package com.project.freeq.service;

import com.project.freeq.repo.ServiceRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceService")
@AllArgsConstructor
public class ServiceService {
    @Autowired
    private final ServiceRepo serviceRepo;

    public List<com.project.freeq.model.Service> getAll(){
        return serviceRepo.findAll();
    }

    public void saveService(com.project.freeq.model.Service service){
        serviceRepo.save(service);
    }

    public void removeService(Long id){
        serviceRepo.deleteById(id);
    }
}
