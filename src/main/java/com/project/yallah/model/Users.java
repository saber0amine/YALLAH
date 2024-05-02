package com.project.yallah.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Table
@Entity
@Inheritance(strategy =  InheritanceType.JOINED)
public class Users {

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

    @Lob
    @Column(name = "UserPicture"   , columnDefinition = "MEDIUMBLOB")
    private byte[] profilePicture ;

    @OneToMany(mappedBy = "bookedUsers"  , cascade = CascadeType.REMOVE)
    List<Activity> bookedActivities ;

    // ADMIN , USER , ORGANISATEUR
    @Enumerated(EnumType.STRING)
    private  UserRole role ;




    public Users() {
    }
    public Users(  String name, String email , String password) {
             this.name = name;
            this.email = email;
            this.password = password;
        }


        /************************************************************************************************************************************************/
        // Organisateur Attributes  :
        @OneToMany(mappedBy = "user"  , cascade = CascadeType.REMOVE)
        List<Activity> activities ;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Activity activity) {
        this.activities.add(activity);
    }

    @Enumerated(EnumType.STRING)
    private  GovernmentIdType governmentIdType  ;

    @Lob
    @Column(name = "IdentityPicture", columnDefinition = "MEDIUMBLOB")
    private byte[] IdentityPicture;
    }

