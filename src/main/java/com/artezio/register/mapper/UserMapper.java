package com.artezio.register.mapper;

import com.artezio.register.dto.UserDto;
import com.artezio.register.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);
    User mapToEntity(UserDto userDto);
}