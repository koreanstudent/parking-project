package com.kr.parking_project.domain.user;

import com.kr.parking_project.api.login.dto.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Account account = findUserByPhoneNumber(phoneNumber);

        return new User(account.getPhoneNumber(), account.getPassword(),authorities(account.getRole().name()));
    }

    public Account findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findUserByPhoneNumber(phoneNumber)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException(phoneNumber));
    }

    // 권한 부여
    private static Collection<? extends GrantedAuthority> authorities(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            addAuthority(authorities, role);
        }
        return authorities;
    }

    private static void addAuthority(List<GrantedAuthority> authorities, String role) {
        if (role != null && !role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        authorities.add(new SimpleGrantedAuthority(role));
    }
}
