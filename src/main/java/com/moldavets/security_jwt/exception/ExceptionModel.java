package com.moldavets.security_jwt.exception;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExceptionModel {
    int status;
    String message;
    Date timestamp;

    public ExceptionModel(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
