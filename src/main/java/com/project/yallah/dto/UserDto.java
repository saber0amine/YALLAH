package com.project.yallah.dto;

import com.project.yallah.model.Activity;
import com.project.yallah.model.UserRole;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data

public class UserDto {
    private UUID id;


    private String name;


    private String email;

    private String password;


    private byte[] profilePicture ;


    private Date age;


// as User
    private List<Activity> bookedActivities;

    //as Organizer
    private List<Activity> activities;
    private UserRole role ;
}
