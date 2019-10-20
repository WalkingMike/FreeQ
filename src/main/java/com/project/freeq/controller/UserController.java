package com.project.freeq.controller;

import com.project.freeq.model.User;
import com.project.freeq.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private final UserService usrService;

    @GetMapping(value = "/getall")
    public @ResponseBody List<User> selectAll() {
        return usrService.getAll();
    }

    @GetMapping(value = "/id/")
    public @ResponseBody User getUserByID(@RequestParam Long id) {
        return usrService.getUserByID(id);
    }

    @GetMapping(value = "/phone/")
    public @ResponseBody User getUserByPhone(@RequestParam String phone) {
        return usrService.getUserByPhone(phone);
    }

    @PostMapping(value = "/add")
    public void addUser(@RequestBody User usr) {
        usrService.saveUser(usr);
    }

    @GetMapping(value = "/getposition")
    public @ResponseBody List<BigDecimal> getPosition (@RequestParam Long id) {
        return usrService.getPosition(id);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and UserPrincipalDetailsService.isSame(principal, id))")
    @PutMapping(value = "/changeposition")
    public void changePosition (@RequestParam Long id, @RequestParam BigDecimal lon, @RequestParam BigDecimal lat) {
        usrService.changePosition(id, lon, lat);
    }

    @PreAuthorize("hasRole('ADMIN') or (hasRole('USER') and UserPrincipalDetailsService.isSame(principal, id))")
    @PostMapping(value = "/modify")
    public void modifyUser(@RequestBody User usr) {
        usrService.modifyUser(usr);
    }

    @DeleteMapping(value = "/remove")
    public void removeUser(@RequestParam Long id) {
        usrService.removeUser(id);
    }

    @DeleteMapping(value = "/remove/phone")
    public void removeUserByPhone(@RequestParam String phone) {
        usrService.removeUserByPhone(phone);
    }
}