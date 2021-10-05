package com.kr.parking_project.user;

import java.util.Optional;

public interface UserRepositoryCustom {
    /**
     * 로그인 아이디로 사용자 조회
     */
    Optional<User> findUserByPhoneNumber(String loginId);

    /**
     * 중복 사용자 조회 (로그인 아이디)
     */
    boolean existsUserByPhoneNumber(String phoneNumber);

}
