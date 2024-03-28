package com.lolpee.server.domain.user.entity;

import com.lolpee.server.domain.auth.attribute.OAuth2Attribute;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_users_username", columnNames = {"username"}),
        @UniqueConstraint(name = "unique_users_email", columnNames = {"email"})
    }
)
@Builder
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USER")
    private RoleType roleType = RoleType.USER;

    @ColumnDefault("false")
    private boolean isEmailVerified = false;

    @ColumnDefault("false")
    private boolean isLocked = false;

    /**
     * OAuth2 요청을 통한 사용자 생성 처리
     * @param attribute 리소스 서버로부터 받은 사용자 정보
     * @param provider 인증 요청을 받은 리소스 서버 종류
     * @return 새롭게 생성된 유저 객체
     */
    public static User createUserByOAuth(OAuth2Attribute attribute, ProviderType provider) {
        return new User.UserBuilder().username(attribute.getId())
                .password("newPassword")
                .email(attribute.getEmail())
                .providerType(provider)
                .roleType(RoleType.USER)
                .isEmailVerified(false)
                .isLocked(false)
                .build();
    }
}
