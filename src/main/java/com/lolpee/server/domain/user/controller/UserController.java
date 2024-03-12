package com.lolpee.server.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/v1/users"})
public class UserController {
    /**
     * 소셜 로그인 기능: 깃허브 로그인
     */
    @PostMapping(value = {"/signIn/github"})
    public void signInWithGithub() {}

    /**
     * 소셜 로그인 기능: 카카오 로그인
     */
    @PostMapping(value = {"/signIn/kakao"})
    public void signInWithKakao() {}

    /**
     * 소셜 로그인 기능: 구글 로그인
     */
    @PostMapping(value = {"/signIn/google"})
    public void signInWithGoogle() {}

    /**
     * 로그아웃 기능 (Redis 사용)
     */
    @PostMapping(value = {"/signOut"})
    public void signOut() {}

    /**
     * 현재 사용자 정보 조회
     */
    @GetMapping(value = {"/"})
    public void searchProfile() {}

    /**
     * 현재 사용자 데이터 수정
     */
    @PatchMapping(value = {"/"})
    public void updateProfile() {}


    /**
     * 특정한 프로필 조회
     * @param profileId: 조회할 프로필 정보
     */
    @GetMapping(value = {"/{profileId}"})
    public void searchProfileById(@PathVariable("profileId") Long profileId) {}

    /**
     * 사용자 팔로우
     * @param profileId: 팔로우할 프로필 정보
     */
    @PostMapping(value = {"/follow/{profileId}"})
    public void createFollow(@PathVariable("profileId") Long profileId) {}

    /**
     * 사용자 언팔로우
     * @param profileId: 언팔로우할 프로필 정보
     */
    @DeleteMapping(value = {"/follow/{profileId}"})
    public void removeFollow(@PathVariable("profileId") Long profileId) {}
}
