package org.example.boardservice.dto.response;

import org.example.boardservice.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.Set;

public class ArticleResponse {
    Long id;
    String title;
    String content;
    String hashtags;
    LocalDateTime createdAt;
    String email;
    String nickname;

    public ArticleResponse(Long id, String title, String content, String hashtags, LocalDateTime createdAt, String email, String nickname) {
    }

    public static ArticleResponse of(Long id, String title, String content, String hashtags, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
    }

    public static ArticleResponse from(ArticleDto dto){
        String nickname = dto.getUserAccountDto().getNickname();
        if (nickname == null || nickname.isEmpty()) {
            nickname = dto.getUserAccountDto().getUserId();
        }

        return new ArticleResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getHashtag(),
                dto.getCreatedAt(),
                dto.getUserAccountDto().getEmail(),
                nickname
        );
    }
}
