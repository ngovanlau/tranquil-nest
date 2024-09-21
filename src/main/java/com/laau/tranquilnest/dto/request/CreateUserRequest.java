package com.laau.tranquilnest.dto.request;

import com.laau.tranquilnest.enums.Role;
import com.laau.tranquilnest.validator.EmailConstraint;
import com.laau.tranquilnest.validator.RoleConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotNull(message = "USERNAME_INVALID")
    String username;
    @NotNull(message = "PASSWORD_INVALID")
    String password;
    @NotNull(message = "FULLNAME_INVALID")
    String fullName;
    @EmailConstraint(message = "EMAIL_INVALID")
    String email;
    String phone;
    @RoleConstraint(message = "ROLE_INVALID")
    String role;
}
