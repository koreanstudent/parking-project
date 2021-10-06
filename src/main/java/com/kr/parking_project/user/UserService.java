package com.kr.parking_project.user;


import com.kr.parking_project.api.user.dto.UserRes;
import com.kr.parking_project.api.user.dto.UserSaveReq;
import com.kr.parking_project.exception.BusinessException;
import com.kr.parking_project.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    public Long saveUser(UserSaveReq userDto) {
        // 패스워드 암호화
        String rawPassword = userDto.getPhoneNumber();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        userDto.changePassword(encodedPassword);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User userEntity = modelMapper.map(userDto, User.class);

        return userRepository.save(userEntity).getId();
    }

    /**
     * [사용자] 단건 조회
     */
    public UserRes findUser(String phoneNumber) {
        return  userRepository.findUserByPhoneNumber(phoneNumber)
                .map(UserRes::new)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_USER));

    }

}
