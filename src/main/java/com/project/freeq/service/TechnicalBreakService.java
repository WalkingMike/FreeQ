package com.project.freeq.service;

import com.project.freeq.model.TechnicalBreak;
import com.project.freeq.repo.TechnicalBreakRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("technicalBreakService")
@AllArgsConstructor
public class TechnicalBreakService {
    @Autowired
    private final TechnicalBreakRepo tBreakRepo;

    public List<TechnicalBreak> getAll(){
        return tBreakRepo.findAll();
    }

    public void modifyTechnicalBreak(TechnicalBreak tBreak){
        TechnicalBreak modTechnicalBreak = tBreakRepo.getOne(tBreak.getId());
        if (null != modTechnicalBreak) {
            tBreakRepo.save(tBreak);
        }
    }
    
    public void saveTechnicalBreak(TechnicalBreak tBreak){
        tBreakRepo.save(tBreak);
    }

    public void removeTechnicalBreak(Long id){
        tBreakRepo.deleteById(id);
    }
}
