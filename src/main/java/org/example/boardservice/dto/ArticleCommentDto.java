package org.example.boardservice.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.boardservice.domain.ArticleComment}
 */
@Value
public class ArticleCommentDto implements Serializable {
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime modifiedAt;
    String modifiedBy;
    String content;

    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, String content) {
        return new ArticleCommentDto(createdAt, createdBy,modifiedAt,modifiedBy,content);
    }
}