package org.example.boardservice.service;

import lombok.RequiredArgsConstructor;
import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.ArticleComment;
import org.example.boardservice.domain.UserAccount;
import org.example.boardservice.domain.type.SearchType;
import org.example.boardservice.dto.ArticleCommentDto;
import org.example.boardservice.dto.ArticleDto;
import org.example.boardservice.dto.ArticleUpdateDto;
import org.example.boardservice.repository.ArticleCommentRepository;
import org.example.boardservice.repository.ArticleRepository;
import org.example.boardservice.repository.UserAccountRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
    private final UserAccountRepository userAccountRepository;

    public List<ArticleCommentDto> searchArticleComment(long l) {
        return Arrays.asList();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.());
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.getUserAccountDto().getUserId());
            ArticleComment articleComment = dto.toEntity(article, userAccount);

            if (dto.getParentCommentId() != null) {
                ArticleComment parentComment = articleCommentRepository.getReferenceById(dto.getParentCommentId());
                parentComment.addChildComment(articleComment);
            } else {
                articleCommentRepository.save(articleComment);
            }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void deleteArticleComment(Long articleCommentId, String userId) {
        articleCommentRepository.deleteByIdAndUserAccount_UserId(articleCommentId, userId);
    }
}
