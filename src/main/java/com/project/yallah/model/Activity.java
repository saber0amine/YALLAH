package com.project.yallah.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @ManyToOne
    @JoinColumn(name = "organisteur_id")
    private Users organisteur ;

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

    @Lob
    @Column(name = "ActivityImages" , columnDefinition = "MEDIUMBLOB")
    private List<byte[]> ActivityImages;
    private String category;
    private int age;



    public Activity() {

    }
}
