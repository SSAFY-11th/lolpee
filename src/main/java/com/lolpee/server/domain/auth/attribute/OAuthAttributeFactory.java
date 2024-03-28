package com.lolpee.server.domain.auth.attribute;

import com.lolpee.server.domain.auth.attribute.impl.GithubAttribute;
import com.lolpee.server.domain.auth.attribute.impl.GoogleAttribute;
import com.lolpee.server.domain.user.entity.ProviderType;

import java.util.Map;

public class OAuthAttributeFactory {
    public static OAuth2Attribute parseOAuthAttribute(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GITHUB -> {
                return new GithubAttribute(attributes);
            }
            case GOOGLE -> {
                return new GoogleAttribute(attributes);
            }
        }
        throw new RuntimeException("");
    }
}
