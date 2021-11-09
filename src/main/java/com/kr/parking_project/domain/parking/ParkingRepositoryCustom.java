package com.kr.parking_project.domain.parking;

public interface ParkingRepositoryCustom {
    boolean existsParkingByNumber(String number);
}
