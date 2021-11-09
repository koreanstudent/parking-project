package com.kr.parking_project.domain.parking;

import com.kr.parking_project.domain.common.BaseEntity;
import com.kr.parking_project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="parking_table")
@Entity
public class Parking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="parking_id")
    private Long id;

    @Column(name="parking_number")
    private String number;

    @Column(name="parking_place")
    private String place;

    @Column(name="parking_phone_number")
    private String phoneNumber;

    @Column(name="parking_state")
    private ParkingState state;

    @Column(name="parking_discount_fee")
    private int discountFee;

    @Column(name="parking_regular")
    private String regular;

    @Column(name="parking_entrance_time")
    private LocalDateTime entranceTime;

    @Column(name="parking_exit_time")
    private LocalDateTime exitTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false, foreignKey = @ForeignKey(name="FK_user_id"))
    private User user;


}
