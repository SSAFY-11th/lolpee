package com.lolpee.server.domain.follow.entity;

import com.lolpee.server.domain.user.entity.User;
import jakarta.persistence.*;

public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User following;
}
