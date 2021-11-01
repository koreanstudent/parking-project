package com.kr.parking_project.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="user_table")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Long id;


    @Column(name="user_name", length = 16 )
    @NotNull
    private String name;

    @Column(name="user_pasword",  length = 128)
    @NotNull
    private String password;

    @Column(name = "user_phone_number", length = 11, unique = true)
    @NotNull
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", length = 16)
    @NotNull
    private UserRole role; // 사용자구분

    public void update(String name, String password, String phoneNumber, UserRole role){
        if(name != null) this.name = name;
        if(password!= null) this.password = password;
        if(phoneNumber!= null) this.phoneNumber = phoneNumber;
        if(role!= null) this.role = role;
    }





}
