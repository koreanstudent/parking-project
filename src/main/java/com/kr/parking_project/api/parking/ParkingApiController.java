package com.kr.parking_project.api.parking;

import com.kr.parking_project.api.parking.dto.ParkingSaveReq;
import com.kr.parking_project.domain.parking.ParkingService;
import com.kr.parking_project.response.Result;
import com.kr.parking_project.utill.HttpUtill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/parking")
@Slf4j
public class ParkingApiController {

    private final ParkingService parkingService;


    /**
     * [주차] 입차 등록
     */
    @PostMapping
    public ResponseEntity saveParking(@RequestBody ParkingSaveReq saveReq) throws URISyntaxException {
        Long createdId = parkingService.saveParking(saveReq);

        return ResponseEntity.created(HttpUtill.getCurrentUri(createdId))
                .body(Result.success());
    }

}
