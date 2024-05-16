package com.project.yallah.dto;

import java.util.Date;


import lombok.Data;

@Data
public class UserRegistrationRequest {
    private String name;
    private String email;
    private String password;
    private Date age;
    private byte[] profilePicture;

}
