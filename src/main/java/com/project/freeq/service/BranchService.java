package com.project.freeq.service;

import com.project.freeq.model.Branch;
import com.project.freeq.repo.BranchRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("branchService")
@AllArgsConstructor
public class BranchService {
    @Autowired
    private final BranchRepo branchRepo;

    public List<Branch> getAll(){
        return branchRepo.findAll();
    }

    public List<Branch> getAllByPartnerID(Long id){
        return branchRepo.findAllByPartnerID(id);
    }

    public void saveBranch(Branch branch){
        branchRepo.save(branch);
    }

    public void removeBranch(Long id){
        branchRepo.deleteById(id);
    }
}
