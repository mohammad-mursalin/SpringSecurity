package com.example.SpringSecurity.service;

import com.example.SpringSecurity.model.Users;
import com.example.SpringSecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager manager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return repo.save(user);
    }

    public String verify(Users user) {

        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken();

        return "fail";
    }
}
