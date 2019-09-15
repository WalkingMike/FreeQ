package com.project.freeq.service;

import com.project.freeq.model.Partner;
import com.project.freeq.repo.PartnerRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service("partnerService")
@AllArgsConstructor
public class PartnerService{

    @Autowired
    private final PartnerRepo partnerRepo;

    public List<Partner> getAll(){
        return partnerRepo.findAll();
    }

    public Partner getPartnerByID(Long id){
        Partner prtn = partnerRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return prtn;
    }

    public Partner getPartnerByPhone(String phone){
        Partner partner = partnerRepo.findByPhone(phone);
        return partner;
    }

    public Partner getPartnerByEmail(String email){
        Partner partner = partnerRepo.findByEmail(email);
        return partner;
    }

    public Partner getPartnerByLogin(String login){
        Partner partner = partnerRepo.findByLogin(login);
        return partner;
    }

    public void savePartner(Partner prtn){
        partnerRepo.save(prtn);
    }

    public void removePartner(Long id){
        partnerRepo.deleteById(id);
    }

    public void removePartnerByPhone(String phone){
        partnerRepo.deleteByPhone(phone);
    }
}