package com.vc.app.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || StringUtils.isBlank(name)) {
			return null;
		}
		Cookie cookie = null;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals(name)) {
				return cookie;
			}
		}
		return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return (cookie != null) ? cookie.getValue() : null;
	}

	public static void deleteCookie(HttpServletResponse response, Cookie cookie, String path) {
		if (cookie != null) {
			if (StringUtils.isBlank(path)) {
				path = "/";
			}
			cookie.setPath(path);
			cookie.setValue("");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	public static void deleteCookie(HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath("/");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (name != null) {
			Cookie cookie = getCookie(request, name);
			if (cookie != null) {
				cookie.setPath("/");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value, int maxAge, String domain, String path, boolean httpOnly) {
		if (value == null) {
			value = "";
		}
		if (StringUtils.isBlank(path)) {
			path = "/";
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		// domain不为空则cookie的domain
		if (!StringUtils.isBlank(domain)) {
			cookie.setDomain(domain);
		}
		cookie.setHttpOnly(httpOnly);
		response.addCookie(cookie);
	}

    public static String getCookieValue(HttpServletRequest request,
            String name, int type) throws UnsupportedEncodingException {
		try {
			String value = CookieUtil.getCookieValue(request, name);
			if (type == 1) {
				return value;
			} else { // type=2
				return URLDecoder.decode(value, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		    throw e;
		}
	}

}
