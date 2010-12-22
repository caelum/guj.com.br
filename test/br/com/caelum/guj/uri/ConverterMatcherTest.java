package br.com.caelum.guj.uri;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ConverterMatcherTest {

	@Test
	public void shouldMatchIfURIIsBookmarkable() {
		BookmarkableConverter firstConverter = mock(BookmarkableConverter.class);
		BookmarkableConverter secondConverter = mock(BookmarkableConverter.class);

		when(firstConverter.isBookmarkable()).thenReturn(false);
		when(secondConverter.isBookmarkable()).thenReturn(true);

		List<BookmarkableConverter> list = new LinkedList<BookmarkableConverter>();
		list.add(firstConverter);
		list.add(secondConverter);

		ConverterMatcher allConverters = new ConverterMatcher(list);

		assertTrue(allConverters.oneMatched());
		assertSame(secondConverter, allConverters.getConverter());
	}

	@Test(expected = RuntimeException.class)
	public void shouldNotMatchIfURIIsNotBookmarkable() {
		BookmarkableConverter firstConverter = mock(BookmarkableConverter.class);
		BookmarkableConverter secondConverter = mock(BookmarkableConverter.class);

		when(firstConverter.isBookmarkable()).thenReturn(false);
		when(secondConverter.isBookmarkable()).thenReturn(false);

		List<BookmarkableConverter> list = new LinkedList<BookmarkableConverter>();
		list.add(firstConverter);
		list.add(secondConverter);

		ConverterMatcher allConverters = new ConverterMatcher(list);

		assertFalse(allConverters.oneMatched());
		allConverters.getConverter();
	}
}
