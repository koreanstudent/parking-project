package com.kr.parking_project.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name ="user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;


    @Column(name="user_name")
    private String name;

    @Column(name="user_phone_number", unique = true)
    private String phoneNumber;



}
