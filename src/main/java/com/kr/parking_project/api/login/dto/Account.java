package com.kr.parking_project.api.login.dto;


import com.kr.parking_project.user.User;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private Long id;
    private String name;
    private String phoneNumber;



    public Account(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber =entity.getPhoneNumber();


    }
}