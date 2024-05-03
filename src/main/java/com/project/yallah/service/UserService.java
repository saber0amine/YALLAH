package com.project.yallah.service;

import com.project.yallah.model.Activity;
import com.project.yallah.model.Users;
import com.project.yallah.repository.ActiviyRepository;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.security.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private ActiviyRepository activiyRepository ;
    private UsersRepository usersRepository  ;
     @Autowired
    public UserService(ActiviyRepository activiyRepository , UsersRepository usersRepository) {
        this.activiyRepository = activiyRepository;
        this.usersRepository = usersRepository;
    }

    public Boolean bookActivity(UUID id , Users user) {
Activity activity = activiyRepository.findById(id)
    .orElseThrow(() -> new EntityNotFoundException("Activity not found with id " + id));
if (activity != null) {
    try {
        user.setBookedActivities(activity);
        usersRepository.save(user);
        return true ;
    }
    catch (Exception e) {
        return false ;
    }
}
    return false ;
    }
}
