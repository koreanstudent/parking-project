package com.kr.parking_project.user;

import com.kr.parking_project.api.login.dto.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = findUserByLoginId(loginId);
        log.debug("LoginService account {}", account);
        return new User(account.getName(), account.getPhoneNumber(),true,true,true,true, new ArrayList<>());
    }

    public Account findUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));
    }
}
