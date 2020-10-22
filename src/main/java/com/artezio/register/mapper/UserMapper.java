package com.artezio.register.mapper;

import com.artezio.register.model.dto.UserDto;
import com.artezio.register.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToDto(User user);
    User mapToEntity(UserDto userDto);
}