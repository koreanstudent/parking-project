package com.kr.parking_project.domain.parking;

import com.kr.parking_project.common.EnumMapperType;
import com.kr.parking_project.domain.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ParkingState implements EnumMapperType {

    ENTRANCE("입차완료"),
    REQUEST("출차요청"),
    EXIT("출/회차완료"),
    ROUND("정기주차");

    private final String title;

    public static ParkingState of(String title) {
        return Arrays.stream(values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException(title));
    }

    @Override
    public String getCode() {
        return name();
    }

}
