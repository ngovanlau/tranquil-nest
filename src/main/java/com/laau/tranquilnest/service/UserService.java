package com.laau.tranquilnest.service;

import com.laau.tranquilnest.dto.request.CreateUserRequest;
import com.laau.tranquilnest.dto.response.UserResponse;
import com.laau.tranquilnest.exception.AppException;

import java.util.List;

public interface UserService {
    void createUser(CreateUserRequest request);
    UserResponse getUserResponse();
    List<UserResponse> getAllUsers();
}
