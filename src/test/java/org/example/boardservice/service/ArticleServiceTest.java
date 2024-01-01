package org.example.boardservice.service;

import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.dto.ArticleUpdateDto;
import org.example.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks
    private ArticleService sut;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 검색시 게시글 리스트 반환")
    @Test
    void search_articleList() {
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, 아이디, 닉네임, 해시태그로 검색

        org.assertj.core.api.Assertions.assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 조회 시 게시글 반환")
    @Test
    void get_article() {
        ArticleDto article = sut.searchArticle(1L);

        org.assertj.core.api.Assertions.assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력시 게시글을 생성")
    @Test
    void create_article() throws Exception {
        ArticleDto articleDto = ArticleDto.of(LocalDateTime.now(), "son", "title", "content", "#java");
        sut.savaArticle(articleDto);
    }


    @DisplayName("게시글의 ID와 수정정보를 입력하면, 게시글을 수정함")
    @Test
    void update_article() throws Exception {
        ArticleUpdateDto articleUpdateDto = ArticleUpdateDto.of("title", "content", "#java");
        sut.updateArticle(1L, articleUpdateDto);
    }

    @DisplayName("게시글의 ID 입력하면, 게시글을 삭제함")
    @Test
    void delete_article() throws Exception {
        sut.deleteArticle(1L);
    }

}