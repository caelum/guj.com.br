package br.com.caelum.guj.uri.bookmarkable;

import java.util.LinkedList;
import java.util.List;

import br.com.caelum.guj.uri.DefaultCompatibleURIBuilder;
import br.com.caelum.guj.uri.URIConverter;

public class AllBookmarkableToCompatibleConverters {

	public static List<URIConverter> get(String uri) {
		List<URIConverter> converters = new LinkedList<URIConverter>();

		converters.add(new BookmarkablePostToCompatibleURIConverter(uri, new DefaultCompatibleURIBuilder()));
		converters.add(new BookmarkablePrePostToCompatibleURIConverter(uri, new DefaultCompatibleURIBuilder()));

		return converters;
	}
}
