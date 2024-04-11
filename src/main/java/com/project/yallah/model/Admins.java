package com.project.yallah.model;

import jakarta.persistence.Entity;

@Entity
public class Admins extends Users{

    public  Admins(String email, String name, String password) {
        super(email, name, password);
    }
    public Admins() {

    }
}
