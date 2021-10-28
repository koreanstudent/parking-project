package com.kr.parking_project.api.login.dto;

import com.kr.parking_project.user.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto  {

    private Long id;
    private String name;
    private String phoneNumber;
    private UserRole role;
    private String token;
}