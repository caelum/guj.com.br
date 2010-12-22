package br.com.caelum.guj.uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookmarkablePostToCompatibleURIConverter implements BookmarkableConverter {

	private static final int POSTS_PER_PAGE = 15;
	private final Matcher matcher;
	private final boolean succedded;
	private final String page;

	public BookmarkablePostToCompatibleURIConverter(RequestInfo info) {
		this.page = info.getParameter("page");

		// /post/<id-post>/<titulo-post>?page=<pagina>
		Pattern pattern = Pattern.compile("\\/post\\/([0-9]+)\\/([a-zA-Z0-9\\-\\_]+)*");
		this.matcher = pattern.matcher(info.getUri());
		this.succedded = this.matcher.find();
	}

	private String getId() {
		return this.matcher.group(1);
	}

	@Override
	public boolean isBookmarkable() {
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
		return this.page != null && this.page.length() > 0;
	}

	private int getPage() {
		return (Integer.parseInt(this.page) - 1) * POSTS_PER_PAGE;
	}
}
