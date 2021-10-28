package com.kr.parking_project.api.login;

import com.kr.parking_project.api.login.dto.Account;
import com.kr.parking_project.api.login.dto.AccountGetReq;
import com.kr.parking_project.api.login.dto.TokenDto;
import com.kr.parking_project.jwt.JwtFilter;
import com.kr.parking_project.jwt.TokenProvider;
import com.kr.parking_project.response.Result;
import com.kr.parking_project.user.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    private final TokenProvider tokenProvider;
    private final LoginService loginService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    //
    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody AccountGetReq loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getPhoneNumber());
        // .authenticate() 메소드가 실행이 될때 loadUserByUsername 메소드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);
        Account currentUser = new Account();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserPhoneNumber = authentication.getName();
            currentUser = loginService.findUserByPhoneNumber(currentUserPhoneNumber);
        }

        log.debug("authentication.getPrincipal()" + currentUser);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(currentUser.getId(), currentUser.getName(),
                currentUser.getPhoneNumber(),currentUser.getRole(),jwt), httpHeaders, HttpStatus.OK);

    }
}
