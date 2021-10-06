package com.kr.parking_project.api.login;

import com.kr.parking_project.api.login.dto.AccountGetReq;
import com.kr.parking_project.api.user.dto.UserRes;
import com.kr.parking_project.common.BaseTest;
import com.kr.parking_project.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
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
                RestDocumentationRequestBuilders.post("/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        System.out.println(content().json("{'userName':'이창현'}"));
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
                                fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저아이디"),
                                fieldWithPath("data.name").type(JsonFieldType.STRING).description("유저이름"),
                                fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                                fieldWithPath("data.password").type(JsonFieldType.STRING).description("비밀번호"),
                                fieldWithPath("data.permissions").type(JsonFieldType.STRING).description("권한"),
                                fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                fieldWithPath("isSuccess").type(JsonFieldType.BOOLEAN).description("성공여부")

                        )

                ));
    }
}
