package com.eaton.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getDomain(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("Origin");
	}

	public static Long getTerminal() {
		HttpServletRequest request = getHttpServletRequest();
		String terminal = request.getHeader("terminal");

		if (terminal == null) {
			return 1L;
		} else if (terminal.equals("PC")) {
			return 1L;
		} else if (terminal.equals("H5")) {
			return 2L;
		}

		return 1L;
	}
}
