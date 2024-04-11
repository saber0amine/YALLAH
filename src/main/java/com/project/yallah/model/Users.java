package com.project.yallah.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
@Table
@Entity
public class Users {


    public Users() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column
    @NotBlank(message = "Password is mandatory")
    private String password;

    public Users( String email , String name, String password) {
             this.name = name;
            this.email = email;
            this.password = password;
        }
    }

