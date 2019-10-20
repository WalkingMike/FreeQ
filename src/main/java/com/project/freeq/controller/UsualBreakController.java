package com.project.freeq.controller;


import com.project.freeq.model.UsualBreak;
import com.project.freeq.service.UsualBreakService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/ubreak")
public class UsualBreakController {
    @Autowired
    private final UsualBreakService uBreakService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<UsualBreak> selectAll() {
        return uBreakService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/add")
    public void addUsualBreak(@RequestBody UsualBreak uBreak) {
        uBreakService.saveUsualBreak(uBreak);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @PostMapping(value = "/modify")
    public void modifyUsualBreak(@RequestBody UsualBreak uBreak) {
        uBreakService.modifyUsualBreak(uBreak);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('PARTNER')")
    @DeleteMapping(value = "/remove")
    public void removeUsualBreak(@RequestParam Long id) {
        uBreakService.removeUsualBreak(id);
    }
}
