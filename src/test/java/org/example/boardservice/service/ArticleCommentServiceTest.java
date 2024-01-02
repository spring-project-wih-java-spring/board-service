package org.example.boardservice.service;

import org.example.boardservice.dto.ArticleCommentDto;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.repository.ArticleCommentRepository;
import org.example.boardservice.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("비즈니스 로직 - 게시판 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTe1st {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 댓글 리스트 조회")
    @Test
    void search_articlecomment_list() {
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);
    }

    @DisplayName("댓글 정보 입력 시 댓글 저장")
    @Test
    void save_articlecomment_list() {
        ArticleCommentDto articleCommentDto = ArticleCommentDto.of(LocalDateTime.now(), "son",LocalDateTime.now(), "son", "content");
        sut.savaArticleComment(articleCommentDto);

    }
}