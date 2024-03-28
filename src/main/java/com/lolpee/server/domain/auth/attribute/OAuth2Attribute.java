package com.lolpee.server.domain.auth.attribute;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public abstract class OAuth2Attribute {
    protected final Map<String, Object> attributes;

    public abstract String getId();
    public abstract String getName();
    public abstract String getEmail();
    public abstract String getImageUrl();

}
