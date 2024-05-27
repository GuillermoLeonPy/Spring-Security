package com.java.spring.security.examples.authentication.controller;

import com.java.spring.security.examples.authentication.jwt.JwtUtils;
import com.java.spring.security.examples.authentication.jwt.model.AuthenticationRequest;
import com.java.spring.security.examples.authentication.jwt.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "authenticationController")
@RequestMapping(path = "authentication")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @Autowired
    public AuthenticationController(
            @Qualifier("authentication_manager")
            AuthenticationManager authenticationManager,
            @Qualifier("my_custom_user_details_service")
                UserDetailsService userDetailsService,
            @Qualifier("jwt_utils_service")
                JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUserName(),
                        authenticationRequest.getPassword())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
