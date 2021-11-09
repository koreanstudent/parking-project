package com.kr.parking_project.config;


import com.kr.parking_project.domain.common.SecurityAuditorAware;
import com.kr.parking_project.domain.user.LoginService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
public class JpaConfig {
    private final LoginService loginService;
    @Bean
    public SecurityAuditorAware securityAuditorAware(LoginService loginService) {
        return new SecurityAuditorAware(loginService);
    }
}
