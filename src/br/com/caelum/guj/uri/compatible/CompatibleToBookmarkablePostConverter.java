package br.com.caelum.guj.uri.compatible;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.jforum.entities.Topic;
import br.com.caelum.guj.configuration.Configs;
import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.uri.BookmarkableURIBuilder;
import br.com.caelum.guj.uri.URIConverter;

public class CompatibleToBookmarkablePostConverter implements URIConverter {

	private final Matcher matcher;
	private final boolean succedded;
	private final TopicRepository topicRepository;
	private final BookmarkableURIBuilder builder;

	public CompatibleToBookmarkablePostConverter(String uri, TopicRepository topicRepository,
			BookmarkableURIBuilder builder) {
		this.topicRepository = topicRepository;
		this.builder = builder;

		Pattern pattern = Pattern.compile("\\/posts\\/list(\\/([0-9]+))?\\/([0-9]+).java");
		this.matcher = pattern.matcher(uri);
		this.succedded = this.matcher.find();
	}

	@Override
	public boolean isConvertable() {
		return this.succedded;
	}

	@Override
	public String convert() {
		int topicId = Integer.parseInt(this.matcher.group(3));
		Topic topic = this.topicRepository.getById(topicId);

		if (this.isPaginated()) {
			return builder.bookmarkableURL(topicId, topic.getTitle(), pageNumber());
		}
		
		return builder.bookmarkableURL(topicId, topic.getTitle());
	}

	private int pageNumber() {
		int firstPostOnPage = Integer.parseInt(this.matcher.group(2));
		return firstPostOnPage / Configs.POSTS_PER_PAGE + 1;
	}

	private boolean isPaginated() {
		return this.matcher.group(2) != null && !this.matcher.group(2).isEmpty();
	}
}
