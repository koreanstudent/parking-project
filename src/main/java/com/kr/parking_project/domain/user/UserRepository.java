package com.kr.parking_project.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
}
