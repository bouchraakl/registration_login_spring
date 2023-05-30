package com.project.registerSpring.service;

import com.project.registerSpring.requests.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    public String register(RegisterRequest request) {
        return "works";
    }
}
