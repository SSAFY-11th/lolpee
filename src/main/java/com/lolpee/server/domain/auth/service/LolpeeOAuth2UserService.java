package com.lolpee.server.domain.auth.service;

import com.lolpee.server.domain.auth.attribute.OAuth2Attribute;
import com.lolpee.server.domain.auth.attribute.OAuthAttributeFactory;
import com.lolpee.server.domain.user.entity.ProviderType;
import com.lolpee.server.domain.user.entity.RoleType;
import com.lolpee.server.domain.user.entity.User;
import com.lolpee.server.domain.auth.entity.UserPrincipal;
import com.lolpee.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class LolpeeOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
        OAuth2User user = userService.loadUser(userRequest);

        ProviderType provider = ProviderType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2Attribute attribute = OAuthAttributeFactory.parseOAuthAttribute(provider, user.getAttributes());
        User findUser = userRepository.findByUsername(attribute.getId()).orElseGet(
            () -> userRepository.save(User.createUserByOAuth(attribute, provider))
        );

        if (findUser.getProviderType() != provider) {
            log.debug("기존 인증 방식: {}, 새로운 인증 방식: {}", findUser.getRoleType(), provider);
        }

        log.debug("OAUTH2 기반의 로그인 요청 -> provider: {}, user: {}", provider, user);
        return new UserPrincipal(findUser, user.getAttributes());
    }
}
