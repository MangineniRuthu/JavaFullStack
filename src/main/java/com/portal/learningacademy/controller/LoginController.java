package com.portal.learningacademy.controller;

import com.portal.learningacademy.entity.LoginEntity;
import com.portal.learningacademy.entity.LoginResponceEntity;
import com.portal.learningacademy.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


@Autowired
    private UserDetailsService userDetailsService;

@Autowired
    private AuthenticationManager authenticationManager;

@Autowired
    private JwtTokenUtil jwtToken;

@PostMapping("/login")
public ResponseEntity<LoginResponceEntity> login(@RequestBody LoginEntity loginEntity){
    this.doAuthentication(loginEntity.getUserName(),loginEntity.getPassword());
    UserDetails userDetails=userDetailsService.loadUserByUsername(loginEntity.getUserName());
    String token=jwtToken.generateToken(userDetails);
    LoginResponceEntity loginResponceEntity= LoginResponceEntity.builder()
            .jwtToken(token).username(userDetails.getUsername()).build();
    return new ResponseEntity<>(loginResponceEntity, HttpStatus.OK);
}

private void doAuthentication(String username,String password){
    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username,password);
    try{
        authenticationManager.authenticate(authentication);
    }catch (BadCredentialsException badCredentialsException){
        throw new BadCredentialsException("invalid credential exception");
    }
}


}
