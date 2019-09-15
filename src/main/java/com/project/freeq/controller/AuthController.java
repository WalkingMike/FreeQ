package com.project.freeq.controller;


import com.project.freeq.config.message.JwtResponse;
import com.project.freeq.config.message.ResponseMessage;
import com.project.freeq.config.security.JwtProvider;
import com.project.freeq.config.security.UserPrincipal;
import com.project.freeq.model.Partner;
import com.project.freeq.model.User;
import com.project.freeq.service.PartnerService;
import com.project.freeq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PartnerService partnerService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login/user")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrincipal userDetails = UserPrincipal.build(user);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.getUserByPhone(user.getPhone()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Phone number is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/login/partner")
    public ResponseEntity<?> authenticatePartner(@RequestBody Partner partner) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(partner.getLogin(), partner.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrincipal userDetails = UserPrincipal.build(partner);

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/register/partner")
    public ResponseEntity<?> registerPartner(@RequestBody Partner partner) {
        if (partnerService.getPartnerByLogin(partner.getLogin()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Login is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (partnerService.getPartnerByPhone(partner.getPhone()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Phone is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (partnerService.getPartnerByEmail(partner.getEmail()) != null) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating partner's account
        partner.setPassword(encoder.encode(partner.getPassword()));
        partnerService.savePartner(partner);

        return new ResponseEntity<>(new ResponseMessage("Partner registered successfully!"), HttpStatus.OK);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<?> authenticateAdmin(@RequestParam String login, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);

        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrincipal userDetails = UserPrincipal.build();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
}