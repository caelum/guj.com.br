package br.com.caelum.guj.uri.bookmarkable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.uri.CompatibleURIBuilder;
import br.com.caelum.guj.uri.URIConverter;

public class BookmarkablePrePostToCompatibleURIConverter implements URIConverter {

	private final Matcher matcher;
	private final boolean succedded;
	private final CompatibleURIBuilder builder;

	public BookmarkablePrePostToCompatibleURIConverter(String uri, CompatibleURIBuilder builder) {

		this.builder = builder;
		// /prepost/<id-post>/<titulo-post>
		Pattern pattern = Pattern
				.compile("\\/prepost\\/([0-9]+)\\/([0-9]+)\\/([a-zA-Z0-9\\-\\_]+)*");
		this.matcher = pattern.matcher(uri);
		this.succedded = this.matcher.find();
	}

	private String getTopicId() {
		return this.matcher.group(1);
	}

	private String getPostId() {
		return this.matcher.group(2);
	}

	@Override
	public boolean isConvertable() {
		return this.succedded;
	}

	@Override
	public String convert() {
		return builder.compatiblePrePostURL(getTopicId(), getPostId());
	}

}
