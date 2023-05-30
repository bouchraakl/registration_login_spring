package com.project.registerSpring.controller;

import com.project.registerSpring.requests.RegisterRequest;
import com.project.registerSpring.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    public RegisterService registerService;

    @PostMapping
    public String registerRequest(@RequestBody RegisterRequest request){
        return registerService.register(request);
    }

}
