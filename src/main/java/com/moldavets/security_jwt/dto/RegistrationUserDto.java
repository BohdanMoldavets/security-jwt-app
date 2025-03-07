package com.moldavets.security_jwt.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationUserDto {
    String username;
    String password;
    String confirmPassword;
    String email;
}
