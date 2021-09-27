package com.kr.parking_project.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
