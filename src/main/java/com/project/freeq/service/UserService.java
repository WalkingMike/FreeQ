package com.project.freeq.service;

import com.project.freeq.model.User;
import com.project.freeq.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("userService")
@AllArgsConstructor
public class UserService{

    @Autowired
    private final UserRepo userRepo;

    public List<User> getAll(){
        return userRepo.findAll();
    }

    public List<BigDecimal> getPosition(Long id) {
        User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return Stream.of(user.getLongitude(), user.getLatitude()).collect(Collectors.toList());
    }

    public void changePosition(Long id, BigDecimal lon, BigDecimal lat){
        User user = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        user.setLongitude(lon);
        user.setLatitude(lat);
        userRepo.save(user);
    }

    public User getUserByID(Long id){
        User usr = userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        return usr;
    }

    public User getUserByPhone(String phone){
        User user = userRepo.findByPhone(phone);
        return user;
    }

    public User getUserByEmail(String email){
        User user = userRepo.findByEmail(email);
        return user;
    }

    public void modifyUser(User user){
        User modUser = userRepo.getOne(user.getId());
        if (null != modUser) {
            userRepo.save(user);
        }
    }
    
    public void saveUser(User usr){
        userRepo.save(usr);
    }

    public void removeUser(Long id){
        userRepo.deleteById(id);
    }

    public void removeUserByPhone(String phone){
        userRepo.deleteByPhone(phone);
    }
}
