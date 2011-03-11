package br.com.caelum.guj.uri.bookmarkable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.uri.CompatibleURIBuilder;
import br.com.caelum.guj.uri.URIConverter;


public class BookmarkableShortPostToCompatibleURIConverter implements URIConverter{
	private final Matcher matcher;
	private final boolean succedded;
	private final CompatibleURIBuilder builder;
	
	public BookmarkableShortPostToCompatibleURIConverter(String uri, CompatibleURIBuilder builder) {
		this.builder = builder;

		Pattern pattern = Pattern
				.compile("\\/java\\/([0-9]+)$");
		this.matcher = pattern.matcher(uri);
		this.succedded = this.matcher.find();
	}
	
	private String getId() {
		return this.matcher.group(1);
	}

	@Override
	public boolean isConvertable() {
		return succedded;
	}

	@Override
	public String convert() {
		return builder.compatibleURL(getId());
	}
}
