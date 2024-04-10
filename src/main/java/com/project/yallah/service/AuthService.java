package com.project.yallah.service;

import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersRepository  usersRepository  ;
    @Autowired
    public AuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void signup() {
    }

    public String login(String name, String email) {
        this.usersRepository.save(new Users(name , email));
        return "you signed in successfully" ;
    }
}
