package com.project.freeq.controller;


import com.project.freeq.model.TechnicalBreak;
import com.project.freeq.service.TechnicalBreakService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/tbreak")
public class TechnicalBreakController {
    @Autowired
    private final TechnicalBreakService tBreakService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<TechnicalBreak> selectAll() {
        return tBreakService.getAll();
    }

    @PostMapping(value = "/add")
    public void addTechnicalBreak(@RequestBody TechnicalBreak tBreak) {
        tBreakService.saveTechnicalBreak(tBreak);
    }

    @DeleteMapping(value = "/remove")
    public void removeTechnicalBreak(@RequestParam Long id) {
        tBreakService.removeTechnicalBreak(id);
    }
}
