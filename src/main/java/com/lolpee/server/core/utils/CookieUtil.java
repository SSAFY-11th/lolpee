package com.lolpee.server.core.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Optional;

public class CookieUtil {
    public static Optional<Cookie> getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length > 0) {
            return Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(key)).findFirst();
        }
        return Optional.empty();
    }

    public static void addCookie(HttpServletResponse response, String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String key) {
        getCookie(request, key).ifPresent((cookie) -> {
            cookie.setValue("");
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        });
    }
}
