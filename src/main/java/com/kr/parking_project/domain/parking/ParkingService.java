package com.kr.parking_project.domain.parking;

import com.kr.parking_project.api.parking.dto.ParkingSaveReq;
import com.kr.parking_project.domain.user.UserRepository;
import com.kr.parking_project.exception.BusinessException;
import com.kr.parking_project.exception.ErrorCode;
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

        if (parkingRepository.existsParkingByNumber(saveReq.getNumber())) {
            throw new BusinessException(ErrorCode.CONFLICT_USER);
        }


            return parkingRepository.save(saveReq.toEntity()).getId();
    }


}
