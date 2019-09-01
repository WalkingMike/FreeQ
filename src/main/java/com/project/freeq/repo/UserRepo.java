package com.project.freeq.repo;

import com.project.freeq.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByPhone(String phone);
    public void deleteByPhone(String phone);
}
