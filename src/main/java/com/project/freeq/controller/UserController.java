package com.project.freeq.controller;

import com.project.freeq.model.User;
import com.project.freeq.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {
    @Autowired
    private final UserService usrService;

    @GetMapping(value = "/user/getall")
    public @ResponseBody List<User> selectAll() {
        return usrService.getAll();
    }

    @GetMapping(value = "/user/id/")
    public @ResponseBody User getUserByID(@RequestParam Long id) {
        return usrService.getUserByID(id);
    }

    @GetMapping(value = "/user/phone/")
    public @ResponseBody User getUserByPhone(@RequestParam String phone) {
        return usrService.getUserByPhone(phone);
    }

    @PostMapping(value = "/user/add")
    public void addUser(@RequestBody User usr) {
        usrService.saveUser(usr);
    }

    @DeleteMapping(value = "/user/remove")
    public void removeUser(@RequestParam Long id) {
        usrService.removeUser(id);
    }

    @DeleteMapping(value = "/user/remove/phone")
    public void removeUserByPhone(@RequestParam String phone) {
        usrService.removeUserByPhone(phone);
    }
}