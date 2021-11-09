package com.kr.parking_project.domain.parking;

import com.kr.parking_project.domain.user.User;
import com.kr.parking_project.domain.user.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ParkingRepository extends JpaRepository<Parking, Long>, UserRepositoryCustom {

}
