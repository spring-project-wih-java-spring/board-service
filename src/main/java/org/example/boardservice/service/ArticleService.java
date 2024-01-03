package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.dto.ArticleUpdateDto;
import org.example.boardservice.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isEmpty()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        Page<ArticleDto> articles = null;
        switch (searchType) {
            case TITLE:
                articles = articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case CONTENT:
                articles = articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case ID:
                articles = articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case NICKNAME:
                articles = articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
                break;
            case HASHTAG:
                articles = articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
                break;
        }

        return articles;
    }



    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void savaArticle(ArticleDto articleDto) {
    }

    public void updateArticle(long l, ArticleUpdateDto articleUpdateDto) {
    }

    public void deleteArticle(long l) {
    }
}
