package com.project.yallah.dto;

import com.project.yallah.model.ActivityCategorie;
import com.project.yallah.model.Location;
import com.project.yallah.model.Users;
import lombok.Data;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
public class ActivityDto {

    private UUID id ;
    private String name;
    private String description;

    private String organizerName;
    private UUID organizerId;

    private Location location;

    private LocalDateTime dateOfPublish;


    private LocalDateTime dateOfStart;


    private LocalDateTime dateOfEnd;

    private Instant duration ;

    private Long price;
    private int capacity;


    private List<String> ActivityImages;

    private ActivityCategorie activityCategorie;

    // MAX and Min ages
    private int Maxage;
    private int Minage;



}
