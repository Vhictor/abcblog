package com.abc.blog.controller;

import com.abc.blog.model.AppUser;
import com.abc.blog.model.AuthenticationRequest;
import com.abc.blog.model.AuthenticationResponse;
import com.abc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<AuthenticationResponse> createNewAccount (@RequestBody AppUser appUser){
        AuthenticationResponse authenticationResponse = userService.createAccount(appUser);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse authenticationResponse = userService.login(authenticationRequest);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.valueOf(200));
    }

}
