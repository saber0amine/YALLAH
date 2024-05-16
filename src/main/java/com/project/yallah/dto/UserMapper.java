package com.project.yallah.dto;


import com.project.yallah.dto.UserDto;
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
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    UserDto toDTO(Users user);


    Users toEntity(UserDto dto);


    static List<UUID> mapActivitiesToIds(List<Activity> activities) {
        return activities.stream().map(Activity::getId).collect(Collectors.toList());
    }
}

