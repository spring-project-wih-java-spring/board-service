package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.repository.ArticleRepository;
import org.springframework.data.domain.Page;
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
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(long l) {
        return null;
    }
}