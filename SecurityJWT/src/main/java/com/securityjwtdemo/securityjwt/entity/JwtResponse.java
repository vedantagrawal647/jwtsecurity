package com.securityjwtdemo.securityjwt.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String jwtToken;
    private  String username;

}
