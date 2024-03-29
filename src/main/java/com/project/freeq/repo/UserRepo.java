package com.project.freeq.repo;

import com.project.freeq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByPhone(String phone);
    User findByEmail(String email);
    void deleteByPhone(String phone);
}
