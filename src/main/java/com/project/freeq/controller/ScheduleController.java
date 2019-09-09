package com.project.freeq.controller;


import com.project.freeq.model.Schedule;
import com.project.freeq.service.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;

    @GetMapping(value = "/getall")
    public @ResponseBody
    List<Schedule> selectAll() {
        return scheduleService.getAll();
    }

    @PostMapping(value = "/add")
    public void addSchedule(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
    }

    @DeleteMapping(value = "/remove")
    public void removeSchedule(@RequestParam Long id) {
        scheduleService.removeSchedule(id);
    }
}
