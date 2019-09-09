package com.project.freeq.repo;

import com.project.freeq.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepo extends JpaRepository<Branch, Long> {
    List<Branch> findAllByPartnerID(Long id);
}
