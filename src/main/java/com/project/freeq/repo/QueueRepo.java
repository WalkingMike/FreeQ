package com.project.freeq.repo;

import com.project.freeq.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepo extends JpaRepository<Queue, Long> {
    List<Queue> findAllByServiceID(Long id);
}
