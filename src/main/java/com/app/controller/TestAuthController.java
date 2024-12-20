package com.app.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@PreAuthorize("isAuthenticated()")
public class TestAuthController {

/*
    @PreAuthorize("hasAuthority('CREATE')")
*/
    @GetMapping("/get")
    public String hello(){
        return "Hello World";
    }

    /*@PreAuthorize("hasAuthority('CREATE')")*/
    @PostMapping("/post")
    public String helloSecured(){
        return "Hello World 420";
    }

}
