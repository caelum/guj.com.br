package br.com.caelum.guj.uri;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DefaultCompatibleURIBuilderTest {

	@Test
	public void shouldGenerateACompatibleURL() {
		DefaultCompatibleURIBuilder builder = new DefaultCompatibleURIBuilder();
		String uri = builder.compatibleURL("111");

		assertEquals("/posts/list/111.java", uri);
	}

	@Test
	public void shouldGenerateACompatibleURLWithPagination() {
		DefaultCompatibleURIBuilder builder = new DefaultCompatibleURIBuilder();
		String uri = builder.compatibleURL("111", 30);

		assertEquals("/posts/list/30/111.java", uri);
	}

	@Test
	public void shouldGenerateACompatiblePrePostURL() {
		DefaultCompatibleURIBuilder builder = new DefaultCompatibleURIBuilder();
		String uri = builder.compatiblePrePostURL("111", "30");

		assertEquals("/posts/preList/111/30.java", uri);
	}
}
