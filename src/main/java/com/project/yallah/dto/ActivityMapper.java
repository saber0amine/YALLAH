package com.project.yallah.dto;


import com.project.yallah.dto.ActivityDto;
import com.project.yallah.model.Activity;
import com.project.yallah.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    @Mapping(source = "organizer.id" , target = "organizerId" )
    @Mapping(source = "organizer.name" , target = "organizerName" )
    ActivityDto toDTO(Activity activity);


    Activity toEntity(ActivityDto dto);



   default List<ActivityDto> toDTOs(List<Activity> activities) {
        return activities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    static List<UUID> mapUsersToIds(List<Users> users) {
        return users.stream().map(Users::getId).collect(Collectors.toList());
    }
}
