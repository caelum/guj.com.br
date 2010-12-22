package br.com.caelum.guj.uri;

import java.util.List;

public class ConverterMatcher {

	private final List<BookmarkableConverter> converters;
	private BookmarkableConverter theOne;
	private boolean converterFound;

	public ConverterMatcher(List<BookmarkableConverter> converters) {
		this.converters = converters;

		this.find();
	}

	public boolean oneMatched() {
		return this.converterFound;
	}

	public BookmarkableConverter getConverter() {
		if (this.converterFound)
			return this.theOne;
		throw new RuntimeException("No converter found!");
	}

	private void find() {
		for (BookmarkableConverter converter : this.converters) {
			if (converter.isBookmarkable()) {
				this.theOne = converter;
				this.converterFound = true;
				break;
			}

		}
	}

}
