package com.moldavets.security_jwt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
}
