package com.kr.parking_project.api.login;

import com.kr.parking_project.api.login.dto.AccountGetReq;
import com.kr.parking_project.common.BaseTest;
import com.kr.parking_project.domain.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class loginApiTest extends BaseTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("로그인 API 테스트")
    public void login() throws Exception {


        AccountGetReq request = new AccountGetReq();
        request.setName("이창현");
        request.setPhoneNumber("01066722131");
        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/authenticate")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("login-user",
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),
                        requestFields(
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("휴대폰번호")

                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("사용자ID"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("사용자이름"),
                                fieldWithPath("phoneNumber").type(JsonFieldType.STRING).description("사용자휴대폰번호"),
                                fieldWithPath("role").type(JsonFieldType.STRING).description("사용자권한"),
                                fieldWithPath("token").type(JsonFieldType.STRING).description("토큰")
                        )
                ));
    }
}
