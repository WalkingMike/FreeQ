package com.project.freeq.controller;


import com.project.freeq.model.Branch;
import com.project.freeq.service.BranchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/branch")
public class BranchController {
    @Autowired
    private final BranchService branchService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<Branch> selectAll() {
        return branchService.getAll();
    }

    @GetMapping(value = "/getall/partnerid")
    public @ResponseBody List<Branch> selectAllByPartnerID(@RequestParam Long id) {
        return branchService.getAllByPartnerID(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/add")
    public void addBranch(@RequestBody Branch branch) {
        branchService.saveBranch(branch);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/modify")
    public void modifyBranch(@RequestBody Branch branch) {
        branchService.modifyBranch(branch);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @DeleteMapping(value = "/remove")
    public void removeBranch(@RequestParam Long id) {
        branchService.removeBranch(id);
    }
}
