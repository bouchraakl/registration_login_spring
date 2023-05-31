package com.project.registerSpring.service;

import com.project.registerSpring.model.Role;
import com.project.registerSpring.model.User;
import com.project.registerSpring.requests.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    public UserService userService;

    public void register(RegisterDTO request) {

        // TODO: 5/31/2023 INITIATE NEW USER
        userService.signUpOperation(new User(request.getFirstName()
                ,request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                Role.USER));

    }
}
