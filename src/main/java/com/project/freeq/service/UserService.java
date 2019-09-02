package com.project.freeq.service;

import com.project.freeq.model.User;
import com.project.freeq.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;


@Service("userService")
@AllArgsConstructor
public class UserService{

    @Autowired
    private final UserRepo userRepo;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public User getUserByID(Long id){
        User usr = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return usr;
    }

    public User getUserByPhone(String phone){
        User user = userRepo.findByPhone(phone);
        return user;
    }

    public void saveUser(User usr){
        userRepo.save(usr);
    }

    @Transactional
    public void removeUser(Long id){
        userRepo.deleteById(id);
    }

    @Transactional
    public void removeUserByPhone(String phone){
        userRepo.deleteByPhone(phone);
    }
}
