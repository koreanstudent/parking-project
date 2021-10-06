package com.kr.parking_project.user;


import com.kr.parking_project.common.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRole implements EnumMapperType {

    ADMIN("관리자"),
    MASTER("사장님"),
    USER("직원");


    private final String title;

    public static UserRole of(String title) {
        return Arrays.stream(values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException(title));
    }

    @Override
    public String getCode() {
        return name();
    }
}
