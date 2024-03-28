package com.lolpee.server.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity // JPA 엔티티 클래스 -- > DB 테이블과 매핑된다.
@Getter // Lombok 라이브러리 Getter 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자 자동 생성
public class Profile {
    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB Auto Increment
    @Column(name = "profile_id") // 컬럼 해당 필드가 DB의 profile_id와 매핑되도록 설정
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) // 1:1 관계  Profile과 User의 관계   질문 : OneToOne의 앞이 해당 클래스 뒤가 클래스의 필드??
        // Lazy : 지연로딩을 사용하여 필요할 때만 연관된 엔티티를 가져오도록 설정

    // name = "user_id"   -->   JoinColumn 외래키를 지정  Profile엔티티에서 사용할 외래키로 "user_id" 지정
    // referencedColumnName = "user_id" --> 외래키의 식별자 컬럼이름을 지정
    // foreignKey = @ForeignKey(name = "foreign_key_user_profile") -- > 외래키 제약조건의 이름 지정
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", foreignKey = @ForeignKey(name = "foreign_key_user_profile"))
    private User user;


    // Null을 허용하는 컬럼은 @Column 어노테이션 생략 가능
    private String profileImage;


    @Column(unique = true) // 유니크한 필드이다 .
    private String nickname;

}
