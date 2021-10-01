package com.kr.parking_project.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveReq {


    private String name;
    private String password;
    private String phoneNumber;


    public void changePassword(String password) {
        this.password = password;
    }

}
