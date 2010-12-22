package br.com.caelum.guj.uri;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestInfo implements RequestInfo {

	private final HttpServletRequest request;

	public HttpRequestInfo(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String getUri() {
		return this.request.getRequestURI();
	}

	@Override
	public String getParameter(String param) {
		return this.request.getParameter(param);
	}

}
