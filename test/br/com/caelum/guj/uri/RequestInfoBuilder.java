package br.com.caelum.guj.uri;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestInfoBuilder {

	public static RequestInfo aRequestFor(String uri) {
		RequestInfo info = mock(RequestInfo.class);
		when(info.getUri()).thenReturn(uri);
		return info;
	}

	public static RequestInfo aRequestFor(String uri, int page) {
		RequestInfo info = mock(RequestInfo.class);
		when(info.getUri()).thenReturn(uri);
		when(info.getParameter("page")).thenReturn(String.valueOf(page));
		return info;
	}

	public static int inPage(int number) {
		return number;
	}
}
