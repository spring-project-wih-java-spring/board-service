package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.dto.ArticleUpdateDto;
import org.example.boardservice.dto.ArticleWithCommentsDto;
import org.example.boardservice.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
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

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다. - articleId: " + articleId));
    }

    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void savaArticle(ArticleDto articleDto) {
        articleRepository.save(articleDto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.getId());

            if (dto.getTitle() != null) {
                article.setTitle(dto.getTitle());
            }

            if (dto.getContent() != null) {
                article.setContent(dto.getContent());
            }

            article.setHashtag(dto.getHashtag()); // save 없이도 transactional에 의해 영속성 컨텍스트 감지가 됨.
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다. - dto: {}", dto);
        }

    }

    public void deleteArticle(long l) {
    }
}
