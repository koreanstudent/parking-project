package com.kr.parking_project.api.login.dto;


import com.kr.parking_project.user.User;
import com.kr.parking_project.user.UserRole;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private Long id;
    private String name;
    private String password;
    private String phoneNumber;
    private UserRole role;



    public Account(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.password =entity.getPassword();
        this.phoneNumber =entity.getPhoneNumber();
        this.role = entity.getRole();


    }
}
