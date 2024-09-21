package com.laau.tranquilnest.mapper;

import com.laau.tranquilnest.dto.request.CreateUserRequest;
import com.laau.tranquilnest.dto.response.UserResponse;
import com.laau.tranquilnest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User toUser(CreateUserRequest request);

    UserResponse toUserResponse(User user);
}
