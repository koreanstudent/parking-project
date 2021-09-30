package com.kr.parking_project.api.user.dto;

import lombok.Data;

@Data
public class UserSaveReq {


    private String name;
    private String password;
    private String phoneNumber;


    public void changePassword(String password) {
        this.password = password;
    }

}
