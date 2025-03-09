package com.moldavets.security_jwt.service;

import com.moldavets.security_jwt.dto.JwtRequest;
import com.moldavets.security_jwt.dto.JwtResponse;
import com.moldavets.security_jwt.dto.RegistrationUserDto;
import com.moldavets.security_jwt.dto.UserDto;
import com.moldavets.security_jwt.entity.User;
import com.moldavets.security_jwt.exception.ExceptionModel;
import com.moldavets.security_jwt.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {
    private UserService userService;
    private JwtTokenUtils jwtTokenUtils;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserService userService,
                          JwtTokenUtils jwtTokenUtils,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(), authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new ExceptionModel(
                            HttpStatus.UNAUTHORIZED.value(),
                            "Invalid password or login"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(
                    new ExceptionModel(
                            HttpStatus.BAD_REQUEST.value(),
                            "Passwords do not match"),
                    HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(
                    new ExceptionModel(
                            HttpStatus.BAD_REQUEST.value(),
                            "User with username " + registrationUserDto.getUsername() + " already exists"),
                    HttpStatus.BAD_REQUEST);
        }
        User registeredUser = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(
                new UserDto(
                        registeredUser.getId(),
                        registeredUser.getUsername(),
                        registeredUser.getEmail()
                )
        );
    }
}
