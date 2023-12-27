package org.example.boardservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class) // 입력한 컨트롤러만 테스트하도록 함.
class ArticleControllerTest {

    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view] [GET] - 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void get_article_viewpage() throws Exception {
        // given

        // when
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index")) // 뷰 이름 테스트 가능
                .andExpect(model().attributeExists("articles")); // 데이터 검증 가능
        // then
    }

    @DisplayName("[view] [GET] - 게시글 상세 페이지 - 정상 호출")
    @Test
    public void get_article_detail_viewpage() throws Exception {
        // given

        // when
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("articles")) // 데이터 검증 가능
                .andExpect(model().attributeExists("articleComments"));

        // then
    }

    @DisplayName("[view] [GET] - 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void get_article_search_viewpage() throws Exception {
        // given

        // when
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
        // then
    }

    @DisplayName("[view] [GET] - 게시글 해시태그 검색 전용 페이지 - 정상 호출")
    @Test
    public void get_article_search_hashtag_viewpage() throws Exception {
        // given

        // when
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/hashtag"));
        // then
    }
}