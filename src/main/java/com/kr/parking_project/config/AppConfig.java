package com.kr.parking_project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    // spring test - > Content type = application/json;charset=UTF-8 설정
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");

        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;

    }
}
