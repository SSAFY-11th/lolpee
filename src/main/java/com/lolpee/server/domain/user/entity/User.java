package com.lolpee.server.domain.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users") // 엔티티가 매핑될 DB 테이블 이름 지정
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
}
