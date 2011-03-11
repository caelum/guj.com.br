package br.com.caelum.guj.uri.bookmarkable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.configuration.Configs;
import br.com.caelum.guj.uri.CompatibleURIBuilder;
import br.com.caelum.guj.uri.PaginatedURIConverter;

public class BookmarkablePostToCompatibleURIConverter implements PaginatedURIConverter {

	private final Matcher matcher;
	private final boolean succedded;
	private final CompatibleURIBuilder builder;

	public BookmarkablePostToCompatibleURIConverter(String uri, CompatibleURIBuilder builder) {
		this.builder = builder;
		// /post/<id-post>/<titulo-post>?page=<pagina>
		Pattern pattern = Pattern
				.compile("\\/java\\/([0-9]+)\\-([a-zA-Z0-9\\-\\_]+)*(\\/([0-9]+))?");
		this.matcher = pattern.matcher(uri);
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
			return builder.compatibleURL(getId(), getFirstPostToShow());
		} else {
			return builder.compatibleURL(getId());
		}
	}

	private boolean thereIsPage() {
		return this.matcher.group(4) != null && this.matcher.group(4).length() > 0;
	}

	private int getFirstPostToShow() {
		return (Integer.parseInt(this.matcher.group(4)) - 1) * Configs.POSTS_PER_PAGE;
	}
	
	public Integer getPage() {
		if (thereIsPage()) {
			return new Integer(this.matcher.group(4));
		}
		return null;
	}
}
