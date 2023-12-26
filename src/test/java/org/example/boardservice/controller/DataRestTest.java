package org.example.boardservice.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled("spring data rest 통합테스트는 불필요하므로 제외시킴.")
@DisplayName("Data REST API 테스트")
@Transactional // 기본적인 테스트들은 rollback 적용
@AutoConfigureMockMvc // mock mvc 주입
@SpringBootTest
//@WebMvcTest : 데이터 configuration 을 따로 읽지 않음, 설정을 해줘야하지만 귀찮음.
public class DataRestTest {

    private final MockMvc mvc;

    @Autowired
    public DataRestTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[api] 게시글 리스트 조회")
    @Test
    void articleList_select() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }

    @DisplayName("[api] 댓글 단건 조회")
    @Test
    void articleComment_select() throws Exception {
        // given

        // when
        // then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
