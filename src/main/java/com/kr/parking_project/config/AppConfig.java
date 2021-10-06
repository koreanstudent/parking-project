package com.kr.parking_project.config;

import com.kr.parking_project.api.user.dto.UserSaveReq;
import com.kr.parking_project.user.User;
import com.kr.parking_project.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

/*    @Bean
    @Profile({"dev"})
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            UserRepository userRepository;
            @Override
            public void run(ApplicationArguments args) throws Exception {


                ModelMapper mapper = new ModelMapper();
                String encodedPassword = new BCryptPasswordEncoder().encode("01066722131");
                System.out.println("checkNumber ::" + userRepository.existsUserByPhoneNumber("01066722131"));
                if(!userRepository.existsUserByPhoneNumber("01066722131")){
                    UserSaveReq user = UserSaveReq.builder()
                            .name("이창현")
                            .password(encodedPassword)
                            .phoneNumber("01066722131")
                            .build();
                    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
                    User userEntity = mapper.map(user, User.class);
                    userRepository.save(userEntity);
                }
            }
        };
    }*/
}
