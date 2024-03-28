package com.lolpee.server.domain.auth.attribute.impl;

import com.lolpee.server.domain.auth.attribute.OAuth2Attribute;

import java.util.Map;

public class GithubAttribute extends OAuth2Attribute {
    public GithubAttribute(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return super.attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return (String) super.attributes.get("name");
    }

    @Override
    public String getEmail() {
        String email = (String) super.attributes.get("email");
        if (email == null) {
            return getName() + "@gihub.com";
        }
        return email;
    }

    @Override
    public String getImageUrl() {
        return (String) super.attributes.get("avatar_url");
    }
}
