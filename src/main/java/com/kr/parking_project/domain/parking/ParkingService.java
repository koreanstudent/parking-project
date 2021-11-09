package com.kr.parking_project.domain.parking;

import com.kr.parking_project.api.parking.dto.ParkingSaveReq;
import com.kr.parking_project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    @Transactional
    public Long saveParking(ParkingSaveReq saveReq) {
            return parkingRepository.save(saveReq.toEntity()).getId();
    }


}
