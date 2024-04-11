package com.project.yallah.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Organisateur extends Users{
    @OneToMany(mappedBy = "organisteur"  , cascade = CascadeType.REMOVE)
    List<Activity> activities ;

    @Enumerated(EnumType.STRING)
    private  GovernmentIdType governmentIdType  ;

    private String governmentIdCountry;

    @Lob
    @Column(name = "GovernmentIdFront", columnDefinition = "MEDIUMBLOB")
    private byte[] governmentIdFront;

    @Lob
    @Column(name = "GovernmentIdBack", columnDefinition = "MEDIUMBLOB")
    private byte[] governmentIdBack;

    //generate a constructor
    public Organisateur(String email, String name, String password, List<Activity> activities, GovernmentIdType governmentIdType, String governmentIdCountry, byte[] governmentIdFront, byte[] governmentIdBack) {
        super(email, name, password);
        this.activities = activities;
        this.governmentIdType = governmentIdType;
        this.governmentIdCountry = governmentIdCountry;
        this.governmentIdFront = governmentIdFront;
        this.governmentIdBack = governmentIdBack;
    }


    public Organisateur() {

    }
}
