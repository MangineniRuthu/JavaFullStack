package com.portal.learningacademy.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginResponceEntity {

    private String jwtToken;
    private String username;
}
