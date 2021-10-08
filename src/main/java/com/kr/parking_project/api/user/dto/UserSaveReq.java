package com.kr.parking_project.api.user.dto;

import com.kr.parking_project.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveReq {

    @NotBlank
    private String name;
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private UserRole role;


    public void changePassword(String password) {
        this.password = password;
    }

}
