package com.cookie;

import org.apache.commons.lang3.RandomStringUtils;
import javax.servlet.http.Cookie;

public class CookieHandler {

    public Cookie setCookie(Cookie[] p_cookies){

        Cookie retCookie;
        String cookieName = "var_cookie";
        String cookieValue = null;
        String cookieExists = "no";

        if (p_cookies != null && p_cookies.length > 1){
            for (Cookie mCookies : p_cookies) {
                String name = mCookies.getName();
                String value = mCookies.getValue();

                if (name.equals(cookieName)) {
                    cookieValue = value;
                    cookieExists = "yes";
                }
            }
        }

        if (cookieExists.equals("no")) {
            cookieValue = RandomStringUtils.randomAlphabetic(25);
        }
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        retCookie = cookie;

        return retCookie;
    }
}
