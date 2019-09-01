package com.project.freeq.repo;

import com.project.freeq.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepo extends JpaRepository<Queue, Long> {
}
