package br.com.caelum.guj.uri.bookmarkable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.uri.RequestInfo;
import br.com.caelum.guj.uri.URIConverter;

public class BookmarkablePostToCompatibleURIConverter implements URIConverter {

	private static final int POSTS_PER_PAGE = 15;
	private final Matcher matcher;
	private final boolean succedded;

	public BookmarkablePostToCompatibleURIConverter(RequestInfo info) {
		// /post/<id-post>/<titulo-post>?page=<pagina>
		Pattern pattern = Pattern
				.compile("\\/post\\/([0-9]+)\\-([a-zA-Z0-9\\-\\_]+)*(\\/([0-9]+))?");
		this.matcher = pattern.matcher(info.getUri());
		this.succedded = this.matcher.find();
	}

	private String getId() {
		return this.matcher.group(1);
	}

	@Override
	public boolean isConvertable() {
		return this.succedded;
	}

	@Override
	public String convert() {
		if (this.thereIsPage()) {
			return String.format("/posts/list/%d/%s.java", this.getPage(), this.getId());
		} else {
			return String.format("/posts/list/%s.java", this.getId());
		}
	}

	private boolean thereIsPage() {
		return this.matcher.group(4) != null && this.matcher.group(4).length() > 0;
	}

	private int getPage() {
		return (Integer.parseInt(this.matcher.group(4)) - 1) * POSTS_PER_PAGE;
	}
}
