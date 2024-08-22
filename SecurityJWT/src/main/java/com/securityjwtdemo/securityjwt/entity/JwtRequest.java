package com.securityjwtdemo.securityjwt.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtRequest {

    private String username;
    private  String password;

}
