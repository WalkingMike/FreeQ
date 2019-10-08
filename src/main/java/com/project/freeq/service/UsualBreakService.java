package com.project.freeq.service;

import com.project.freeq.model.UsualBreak;
import com.project.freeq.repo.UsualBreakRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usualBreakService")
@AllArgsConstructor
public class UsualBreakService {
    @Autowired
    private final UsualBreakRepo uBreakRepo;

    public List<UsualBreak> getAll(){
        return uBreakRepo.findAll();
    }

    public void modifyUsualBreak(UsualBreak uBreak){
        UsualBreak modUsualBreak = uBreakRepo.getOne(uBreak.getId());
        if (null != modUsualBreak) {
            uBreakRepo.save(uBreak);
        }
    }
    
    public void saveUsualBreak(UsualBreak uBreak){
        uBreakRepo.save(uBreak);
    }

    public void removeUsualBreak(Long id){
        uBreakRepo.deleteById(id);
    }
}
