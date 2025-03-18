package com.spring.security.test.Spring.Security.Test.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/admin")
    public ResponseEntity<String> adminUser(){
        return ResponseEntity.ok("Hi, I am Admin User");
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicUser(){
        return ResponseEntity.ok("Hi, I am Public User");
    }

    @GetMapping("/staff")
    public ResponseEntity<String> staffUser(){
        return ResponseEntity.ok("Hi, I am Staff User");
    }
}
