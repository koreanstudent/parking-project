package com.kr.parking_project.api.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kr.parking_project.user.User;
import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {

    private Long id;
    private String name;
    private String phoneNumber;


    public UserRes(Long id) {
        this.id = id;
    }

    public UserRes(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.phoneNumber = entity.getPhoneNumber();
    }

}
