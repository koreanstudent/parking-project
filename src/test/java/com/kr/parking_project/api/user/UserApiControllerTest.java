package com.kr.parking_project.api.user;

import com.kr.parking_project.api.user.dto.UserSaveReq;
import com.kr.parking_project.common.BaseTest;
import com.kr.parking_project.domain.user.User;
import com.kr.parking_project.domain.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class UserApiControllerTest extends BaseTest {

    @Autowired
    private Validator validate;

    @Test
    @DisplayName("유저 저장 - /api/users")
    public void saveUser() throws Exception {

        String phoneNumber = "01066722133";
        String encodedPassword = new BCryptPasswordEncoder().encode(phoneNumber);


        UserSaveReq request = UserSaveReq.builder()
                .name("이창현")
                .password(encodedPassword)
                .phoneNumber(phoneNumber)
                .role(UserRole.USER)
                .build();



        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/users")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"))
                .andDo(print())
                .andDo(document("create-user",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("password").description("비밀번호"),
                                fieldWithPath("phoneNumber").description("휴대폰번호"),
                                fieldWithPath("role").type(JsonFieldType.STRING).description("권한")
                        )


                ));

    }
}