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

    /* A ManyToMany relationship for normal users booking activities.
   This is represented by the bookedActivities field in the Users entity and the bookedUsers field in the Activity entity. */
    @ManyToMany
    @JoinTable(
            name = "booked_activities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> bookedActivities;




    // ADMIN , USER , ORGANISATEUR
    @Enumerated(EnumType.STRING)
    private  UserRole role ;



        /************************************************************************************************************************************************/
        // Organisateur Attributes  :
    /* A OneToMany relationship for organizers managing their own activities.
    This is represented by the activities field in the Users entity and the user field in the Activity entity.  */
        @OneToMany(mappedBy = "organizer", cascade = CascadeType.ALL)
        private List<Activity> activities;

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Activity activity) {
        this.activities.add(activity);
    }

    public void setBookedActivities(Activity activity) {
        this.bookedActivities.add(activity);
    }

    @Enumerated(EnumType.STRING)
    private  GovernmentIdType governmentIdType  ;

    @Lob
    @Column(name = "IdentityPicture", columnDefinition = "MEDIUMBLOB")
    private byte[] IdentityPicture;


    public Users() {
    }
    public Users(  String name, String email , String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", profilePicture=" + profilePicture +

                '}';
    }

}

