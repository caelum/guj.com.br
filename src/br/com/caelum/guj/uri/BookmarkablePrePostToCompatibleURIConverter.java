package br.com.caelum.guj.uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookmarkablePrePostToCompatibleURIConverter implements BookmarkableConverter {

	private final Matcher matcher;
	private final boolean succedded;

	public BookmarkablePrePostToCompatibleURIConverter(RequestInfo info) {

		// /prepost/<id-post>/<titulo-post>
		Pattern pattern = Pattern
				.compile("\\/prepost\\/([0-9]+)\\/([0-9]+)\\/([a-zA-Z0-9\\-\\_]+)*");
		this.matcher = pattern.matcher(info.getUri());
		this.succedded = this.matcher.find();
	}

	private String getTopicId() {
		return this.matcher.group(1);
	}

	private String getPostId() {
		return this.matcher.group(2);
	}

	@Override
	public boolean isBookmarkable() {
		return this.succedded;
	}

	@Override
	public String convert() {
		return String.format("/posts/preList/%s/%s.java", this.getTopicId(), this.getPostId());
	}

}
