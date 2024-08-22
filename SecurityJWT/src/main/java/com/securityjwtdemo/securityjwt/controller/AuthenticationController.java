package com.securityjwtdemo.securityjwt.controller;


import com.securityjwtdemo.securityjwt.config_security.CustomUserDetailsService;
import com.securityjwtdemo.securityjwt.entity.JwtRequest;
import com.securityjwtdemo.securityjwt.entity.JwtResponse;
import com.securityjwtdemo.securityjwt.jwt.JwtHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("detailsService1")
    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private JwtHelper  jwtHelper;




    @PostMapping("/dologin")
    public ResponseEntity<JwtResponse> doLogin(@ModelAttribute("jwtRequest") JwtRequest jwtRequest, HttpServletResponse response) {

        String username = jwtRequest.getUsername();
        String password = jwtRequest.getPassword();

        ResponseEntity<String> res = this.doAuthenticate(  username, password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = this.jwtHelper.generateToken(userDetails);

        Cookie cookie = new Cookie("jwt",token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        JwtResponse jwtResponse = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);

    }


    private ResponseEntity<String> doAuthenticate(String username, String password) {

        try {
            // Create an authentication token with username and password
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

            // Authenticate the token
            Authentication authentication = authenticationManager.authenticate(authRequest);

            // If authentication is successful, the SecurityContext will be updated
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }


}
