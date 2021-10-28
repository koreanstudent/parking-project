package com.kr.parking_project.user;

import java.util.Optional;

public interface UserRepositoryCustom {
    /**
     * 휴대폰번호로 로그인 사용자 조회
     */
    Optional<User> findUserByPhoneNumber(String phoneNumber);

    /**
     * 로그인 아이디로 사용자 조회
     */
    Optional<User> findUserByUserId(Long userId);

    /**
     * 중복 사용자 조회 (로그인 아이디)
     */
    boolean existsUserByPhoneNumber(String phoneNumber);

}
