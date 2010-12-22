package br.com.caelum.guj.uri;

import java.util.LinkedList;
import java.util.List;

public class AllConverters {

	public static List<BookmarkableConverter> get(RequestInfo info) {
		List<BookmarkableConverter> converters = new LinkedList<BookmarkableConverter>();

		converters.add(new BookmarkablePostToCompatibleURIConverter(info));
		converters.add(new BookmarkablePrePostToCompatibleURIConverter(info));

		return converters;
	}
}
