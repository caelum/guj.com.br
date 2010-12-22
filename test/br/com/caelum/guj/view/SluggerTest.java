package br.com.caelum.guj.view;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SluggerTest {

	@Test
	public void shouldSluggerize() {
		assertEquals("nome-deve-ficar-assim", new Slugger().sluggerize("nome deve ficar assim"));
		assertEquals("nome-com-numero-123", new Slugger().sluggerize("nome com numero 123$"));
		assertEquals("nome-com-numero-123",
				new Slugger().sluggerize("nome com !ö&#[]{}()\\*/%?$numero 123"));
	}
}
