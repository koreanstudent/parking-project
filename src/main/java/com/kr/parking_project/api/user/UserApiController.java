package com.kr.parking_project.api.user;


import com.kr.parking_project.api.user.dto.UserRes;
import com.kr.parking_project.api.user.dto.UserSaveReq;
import com.kr.parking_project.api.user.dto.UserUpdateReq;
import com.kr.parking_project.response.Result;
import com.kr.parking_project.domain.user.UserService;
import com.kr.parking_project.utill.HttpUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class UserApiController {

    private final UserService userService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome to user service";
    }
    /**
     * [사용자] 단건 등록
     */
    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody UserSaveReq saveReq) throws URISyntaxException {
        Long id = userService.saveUser(saveReq);

        return ResponseEntity.created(HttpUtill.getCurrentUri(id))
                .body(Result.success());
    }
    /**
     * [사용자] 단건 조회
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity findUser(@PathVariable Long userId){
        UserRes user = userService.findUser(userId);

        return ResponseEntity.ok(Result.success(user));
    }

    /**
     * [사용자] 단건 수정
     */
    @PutMapping("/users/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateReq updateReq){
        Long updatedId = userService.updateUser(userId,updateReq);

        return ResponseEntity.ok(Result.success(new UserRes(updatedId)));
    }

    /**
     * [사용자] 단건 삭제
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId){
        userService.deleteUser(userId);

        return ResponseEntity.ok(Result.success());
    }


}
