package com.project.freeq.service;


import com.project.freeq.model.Schedule;
import com.project.freeq.repo.ScheduleRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("scheduleService")
@AllArgsConstructor
public class ScheduleService {
    @Autowired
    private final ScheduleRepo scheduleRepo;
    
    public List<Schedule> getAll(){
        return scheduleRepo.findAll();
    }

    public void modifySchedule(Schedule schedule){
        Schedule modSchedule = scheduleRepo.getOne(schedule.getId());
        if (null != modSchedule) {
            scheduleRepo.save(schedule);
        }
    }
    
    public void saveSchedule(Schedule schedule){
        scheduleRepo.save(schedule);
    }

    public void removeSchedule(Long id){
        scheduleRepo.deleteById(id);
    }
}
