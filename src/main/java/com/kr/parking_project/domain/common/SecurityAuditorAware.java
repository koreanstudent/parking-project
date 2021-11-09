package com.kr.parking_project.domain.common;

import com.kr.parking_project.api.login.dto.Account;
import com.kr.parking_project.domain.user.LoginService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SecurityAuditorAware implements AuditorAware<String> {
    private final LoginService loginService;



    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            //return Optional.empty();
            return Optional.ofNullable("SYSTEM");
        }
log.debug("authentication" + authentication.getName());
        Account currentUser = new Account();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserPhoneNumber = authentication.getName();
            currentUser = loginService.findUserByPhoneNumber(currentUserPhoneNumber);
        }
        return Optional.ofNullable(currentUser.getName());
    }
}