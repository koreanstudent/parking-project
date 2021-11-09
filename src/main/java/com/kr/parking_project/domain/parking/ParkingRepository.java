package com.kr.parking_project.domain.parking;


import org.springframework.data.jpa.repository.JpaRepository;

public interface  ParkingRepository extends JpaRepository<Parking, Long>, ParkingRepositoryCustom {


}
