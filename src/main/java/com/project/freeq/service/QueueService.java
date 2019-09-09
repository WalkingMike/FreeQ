package com.project.freeq.service;

import com.project.freeq.model.Queue;
import com.project.freeq.repo.QueueRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("queueService")
@AllArgsConstructor
public class QueueService {
    @Autowired
    private final QueueRepo queueRepo;

    public List<Queue> getAll(){
        return queueRepo.findAll();
    }

    public List<Queue> getAllByServiceID(Long id){
        return queueRepo.findAllByServiceID(id);
    }

    public void saveQueue(Queue queue){
        queueRepo.save(queue);
    }

    public void removeQueue(Long id){
        queueRepo.deleteById(id);
    }
}
