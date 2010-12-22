package br.com.caelum.guj.uri.bookmarkable;

import java.util.List;

import br.com.caelum.guj.uri.URIConverter;

public class ConverterMatcher {

	private final List<URIConverter> converters;
	private URIConverter theOne;
	private boolean converterFound;

	public ConverterMatcher(List<URIConverter> converters) {
		this.converters = converters;

		this.find();
	}

	public boolean oneMatched() {
		return this.converterFound;
	}

	public URIConverter getConverter() {
		if (this.converterFound)
			return this.theOne;
		throw new RuntimeException("No converter found!");
	}

	private void find() {
		for (URIConverter converter : this.converters) {
			if (converter.isConvertable()) {
				this.theOne = converter;
				this.converterFound = true;
				break;
			}

		}
	}

}
