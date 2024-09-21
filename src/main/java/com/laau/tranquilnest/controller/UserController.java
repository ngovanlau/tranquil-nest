package com.laau.tranquilnest.controller;

import com.laau.tranquilnest.dto.ApiResponse;
import com.laau.tranquilnest.dto.request.CreateUserRequest;
import com.laau.tranquilnest.dto.response.UserResponse;
import com.laau.tranquilnest.exception.AppException;
import com.laau.tranquilnest.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping()
    public ApiResponse<String> createUser(@RequestBody @Valid CreateUserRequest request) throws AppException {
        userService.createUser(request);
        return ApiResponse.<String>builder()
                .code(100)
                .result("Thêm người dùng thành công")
                .build();
    }

    @GetMapping("/current-user")
    public ApiResponse<UserResponse> getCurrentUser() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUserResponse())
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUsers())
                .build();
    }
}
