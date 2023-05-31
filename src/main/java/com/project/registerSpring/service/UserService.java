package com.project.registerSpring.service;


import com.project.registerSpring.model.Token;
import com.project.registerSpring.model.User;
import com.project.registerSpring.repository.UserRepository;
import com.project.registerSpring.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Autowired
    public TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public String persistNewUser(User user) {

        // TODO: 5/31/2023 ENCODE PASSWORD
        String encodedPass = passwordEncoder.bCryptPasswordEncoder(user.getPassword()).toString();
        user.setPassword(encodedPass);
        userRepository.save(user);


        // TODO: 5/31/2023 GENERATE NEW TOKEN TO THE USER
        String token = UUID.randomUUID().toString();

        Token tokenGenerated = new Token(user, token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));

        tokenService.saveToken(tokenGenerated);

        return token;
    }

    public void signUpOperation(User user) {

        User userExists = userRepository
                .findByEmail(user.getEmail());

        if (userExists == null) {
            persistNewUser(user);
        }

        throw new IllegalStateException("email already taken");

    }

}
