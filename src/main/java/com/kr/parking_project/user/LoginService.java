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
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Account account = findUserByPhoneNumber(phoneNumber);
        log.debug("LoginService loadUserByUsername {}", account);
        return new User(account.getPhoneNumber(), account.getPassword(),true,true,true,true, new ArrayList<>());
    }

    public Account findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException(phoneNumber));
    }
}
