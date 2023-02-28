package com.masai.demo.controller;

import com.masai.demo.dto.UserDto;
import com.masai.demo.exception.ApiException;
import com.masai.demo.model.JwtAuthRequest;
import com.masai.demo.model.JwtAuthResponse;
import com.masai.demo.model.User;
import com.masai.demo.security.JwtTokenHelper;
import com.masai.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request){

        this.authenticate(request.getEmail(),request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto){

        User user = this.userService.registerUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private void authenticate(String username, String password){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password);

        try{
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch (BadCredentialsException e){
            throw new ApiException("Invalid Username and Password");
        }
    }
}
