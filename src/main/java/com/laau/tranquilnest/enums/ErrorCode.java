package com.laau.tranquilnest.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "Uncategorized Error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(101, "Invalid message key", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(102, "Username cannot be null", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(103, "Password cannot be null", HttpStatus.BAD_REQUEST),
    FULLNAME_INVALID(104, "Fullname cannot be null", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(105, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    USERNAME_EXISTED(106, "Username already exists", HttpStatus.BAD_REQUEST),
    ROLE_INVALID(107, "Role does not exist", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(108, "Email already exists", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(109, "Email invalid", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(110, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(111, "You don't have permission", HttpStatus.FORBIDDEN),
    ;

    int code;
    String message;
    HttpStatus httpStatus;
}
