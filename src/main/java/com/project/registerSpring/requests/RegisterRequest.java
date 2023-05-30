package com.project.registerSpring.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RegisterRequest {

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String password;

}
