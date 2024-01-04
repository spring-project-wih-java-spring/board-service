package org.example.boardservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.boardservice.domain.UserAccount;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserAccountDto{

    private String userId;
    private String userPassword;
    private String email;
    private String nickname;
    private String memo;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public UserAccountDto(String userId, String userPassword, String email, String nickname, String memo, Object o, Object o1, Object o2, Object o3) {
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo) {
        return new UserAccountDto(userId, userPassword, email, nickname, memo, null, null, null, null);
    }

    public static UserAccountDto of(String userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }

}