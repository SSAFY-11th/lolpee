package com.lolpee.server.domain.auth.handler;

import com.lolpee.server.core.properties.AuthProperties;
import com.lolpee.server.domain.auth.attribute.OAuth2Attribute;
import com.lolpee.server.domain.auth.attribute.OAuthAttributeFactory;
import com.lolpee.server.domain.user.entity.ProviderType;
import com.lolpee.server.domain.auth.entity.UserPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final AuthProperties authProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        ProviderType provider = ProviderType.valueOf(authToken.getAuthorizedClientRegistrationId().toUpperCase());
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        OAuth2Attribute attribute = OAuthAttributeFactory.parseOAuthAttribute(provider, user.getAttributes());

        // TODO: JWT 토큰을 발행하는 코드가 필요함.
        // TODO: Refresh 토큰 관리해주는 코드가 필요함.

        // * REDIRECT URI에 accessToken과 refreshToken을 넣어 응답해주는 코드
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(authProperties.getRedirectUrl())
                .queryParam("accessToken", "ACCESS TOKEN 정보 입력");

        getRedirectStrategy().sendRedirect(request, response, uriBuilder.toUriString());
    }

    private void setAndClearAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
    }
}
