package br.com.caelum.guj.uri.bookmarkable;

import java.util.LinkedList;
import java.util.List;

import br.com.caelum.guj.uri.URIConverter;
import br.com.caelum.guj.uri.RequestInfo;

public class AllConverters {

	public static List<URIConverter> get(RequestInfo info) {
		List<URIConverter> converters = new LinkedList<URIConverter>();

		converters.add(new BookmarkablePostToCompatibleURIConverter(info));
		converters.add(new BookmarkablePrePostToCompatibleURIConverter(info));

		return converters;
	}
}
