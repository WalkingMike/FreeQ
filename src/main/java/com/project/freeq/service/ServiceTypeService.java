package com.project.freeq.service;

import com.project.freeq.model.ServiceType;
import com.project.freeq.repo.ServiceTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceTypeService")
@AllArgsConstructor
public class ServiceTypeService {
    @Autowired
    private final ServiceTypeRepo serviceTypeRepo;

    public List<ServiceType> getAll(){
        return serviceTypeRepo.findAll();
    }

    public void saveServiceType(ServiceType serviceType){
        serviceTypeRepo.save(serviceType);
    }

    public void removeServiceType(Long id){
        serviceTypeRepo.deleteById(id);
    }
}