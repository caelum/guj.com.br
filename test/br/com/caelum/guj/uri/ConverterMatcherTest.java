package br.com.caelum.guj.uri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.com.caelum.guj.uri.bookmarkable.ConverterMatcher;

public class ConverterMatcherTest {

	@Test
	public void shouldMatchIfURIIsBookmarkable() {
		URIConverter firstConverter = mock(URIConverter.class);
		URIConverter secondConverter = mock(URIConverter.class);

		when(firstConverter.isConvertable()).thenReturn(false);
		when(secondConverter.isConvertable()).thenReturn(true);

		List<URIConverter> list = new LinkedList<URIConverter>();
		list.add(firstConverter);
		list.add(secondConverter);

		ConverterMatcher allConverters = new ConverterMatcher(list);

		assertTrue(allConverters.oneMatched());
		assertSame(secondConverter, allConverters.getConverter());
	}

	@Test(expected = RuntimeException.class)
	public void shouldNotMatchIfURIIsNotBookmarkable() {
		URIConverter firstConverter = mock(URIConverter.class);
		URIConverter secondConverter = mock(URIConverter.class);

		when(firstConverter.isConvertable()).thenReturn(false);
		when(secondConverter.isConvertable()).thenReturn(false);

		List<URIConverter> list = new LinkedList<URIConverter>();
		list.add(firstConverter);
		list.add(secondConverter);

		ConverterMatcher allConverters = new ConverterMatcher(list);

		assertFalse(allConverters.oneMatched());
		allConverters.getConverter();
	}
}
