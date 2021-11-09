package com.kr.parking_project.api.parking.dto;

import com.kr.parking_project.domain.parking.Parking;
import com.kr.parking_project.domain.parking.ParkingState;

import com.kr.parking_project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
public class ParkingSaveReq {

    private String number;
    private String place;
    private String phoneNumber;
    private ParkingState state;
    private int discountFee;
    private String regular;

    private Long userId;

    private LocalDateTime  setEntranceTime() {
        LocalDateTime now = LocalDateTime.now();
        return  now;
    }

    public Parking toEntity() {
        return Parking.builder()
                .number(number)
                .place(place)
                .phoneNumber(phoneNumber)
                .state(state)
                .discountFee(discountFee)
                .regular(regular)
                .entranceTime(setEntranceTime())
                .user(User.builder().id(userId).build())
                .build();
    }

}

