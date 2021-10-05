package com.kr.parking_project.api.user;


import com.kr.parking_project.api.user.dto.UserRes;
import com.kr.parking_project.api.user.dto.UserSaveReq;
import com.kr.parking_project.response.Result;
import com.kr.parking_project.user.UserService;
import com.kr.parking_project.utill.HttpUtill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to user service";
    }
    /**
     * [사용자] 단건 등록
     */
    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody UserSaveReq saveReq) throws URISyntaxException {
        Long id = userService.saveUser(saveReq);

        return ResponseEntity.created(HttpUtill.getCurrentUri(id))
                .body(Result.success());
    }
    /**
     * [사용자] 단건 조회
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity findUser(@PathVariable String phoneNumber){
        UserRes user = userService.findUser(phoneNumber);

        return ResponseEntity.ok(Result.success(user));
    }


}
