package com.project.yallah.service;

import com.project.yallah.contollers.AuthController;
import com.project.yallah.model.Activity;
import com.project.yallah.model.Users;
import com.project.yallah.repository.ActiviyRepository;
import com.project.yallah.repository.UsersRepository;
import com.project.yallah.security.AuthenticatedUser;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

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
        e.printStackTrace();
    }
}
    return false ;
    }

    public Boolean cancelActivity(UUID id, Users user) {
         try {
             Activity activity  =   activiyRepository.findById(id)
                     .orElseThrow(() -> new EntityNotFoundException("Activity not found with id " + id)) ;
         user.getBookedActivities().remove(activity);
         LOG.info("ALL USERS  STILL IN the ACTIVITY " , activity. getBookedUsers()  ) ;
         usersRepository.save(user) ;
                    return  true  ;
         }
            catch (Exception e) {
             e.printStackTrace();
             }
         return false ;
    }
}
