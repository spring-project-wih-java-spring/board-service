package org.example.boardservice.service;

import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


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
        List<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); // 제목, 본문, 아이디, 닉네임, 해시태그로 검색

        org.assertj.core.api.Assertions.assertThat(articles).isNotNull();
    }
}