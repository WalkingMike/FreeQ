package com.project.freeq.repo;

import com.project.freeq.model.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepo extends JpaRepository<Partner, Long>{
    Partner findByLogin(String phone);
    Partner findByPhone(String phone);
    Partner findByEmail(String email);
    void deleteByPhone(String phone);
}
