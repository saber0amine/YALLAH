package com.project.yallah.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Table
public class Activity {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id ;
    private String name;
    private String description;

    // ManyToMany relationship for being booked by users
    //@JsonManagedReference("user-Bookedactivity")
     @ManyToMany(mappedBy = "bookedActivities")
    private List<Users> bookedUsers;

    // ManyToOne relationship for being managed by an organizer
    //@JsonManagedReference("user-organizer")
    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private Users organizer;


    @Embedded
    private Location location;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDateTime dateOfPublish;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateOfStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateOfEnd;

   private Instant duration ;

    private Long price;
    private int capacity;

    @ElementCollection
    @Column(name = "ActivityImages")
    private  List<String> ActivityImages;

    @Enumerated(EnumType.STRING)
    private ActivityCategorie activityCategorie;

    // MAX and Min ages
    private int Maxage;
    private int Minage;


    // ACCEPTED OR REJECTED , PENDING FROM THE ADMIN
    @Enumerated(EnumType.STRING)
    private ActivityStatus status ;



    public Activity() {

    }
    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", dateOfPublish=" + dateOfPublish +
                ", dateOfStart=" + dateOfStart +
                ", dateOfEnd=" + dateOfEnd +
                '}';
    }


}
