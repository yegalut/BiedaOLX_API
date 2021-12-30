package com.example.biedaolx_api.controller;

import com.example.biedaolx_api.dto.LoginCredentials;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("**")
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestParam("username") String username, @RequestParam("password") String password){


    }

}
