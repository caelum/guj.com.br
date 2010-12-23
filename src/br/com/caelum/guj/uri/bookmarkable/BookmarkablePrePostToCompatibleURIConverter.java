package br.com.caelum.guj.uri.bookmarkable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.uri.URIConverter;

public class BookmarkablePrePostToCompatibleURIConverter implements URIConverter {

	private final Matcher matcher;
	private final boolean succedded;

	public BookmarkablePrePostToCompatibleURIConverter(String uri) {

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
		return String.format("/posts/preList/%s/%s.java", this.getTopicId(), this.getPostId());
	}

}
