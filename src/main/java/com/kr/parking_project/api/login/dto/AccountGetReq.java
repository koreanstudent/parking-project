package com.kr.parking_project.api.login.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AccountGetReq {

    @NotBlank
    @Size(max = 16)
    private String name;

    @NotBlank
    @Size(max = 128)
    private String phoneNumber;




    private int sessionTimeout;
}