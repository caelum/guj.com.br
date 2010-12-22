package br.com.caelum.guj.uri.compatible;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.jforum.entities.Topic;
import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.uri.RequestInfo;
import br.com.caelum.guj.uri.URIConverter;
import br.com.caelum.guj.view.Slugger;

public class CompatibleToBookmarkablePostConverter implements URIConverter {

	private static final int POSTS_PER_PAGE = 15;
	private final Matcher matcher;
	private final boolean succedded;
	private final TopicRepository topicRepository;
	private final Slugger slugger;

	public CompatibleToBookmarkablePostConverter(RequestInfo info, TopicRepository topicRepository,
			Slugger slugger) {
		this.topicRepository = topicRepository;
		this.slugger = slugger;
		Pattern pattern = Pattern.compile("\\/posts\\/list(\\/([0-9]+))?\\/([0-9]+).java");
		this.matcher = pattern.matcher(info.getUri());
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
		String sluggedTitle = this.slugger.sluggerize(topic.getTitle());

		if (this.isPaginated()) {
			return "/post/" + topicId + "/" + sluggedTitle + "/" + this.pageNumber();
		}
		return "/post/" + topicId + "/" + sluggedTitle;
	}

	private int pageNumber() {
		int firstPostOnPage = Integer.parseInt(this.matcher.group(2));
		return firstPostOnPage / POSTS_PER_PAGE + 1;
	}

	private boolean isPaginated() {
		return this.matcher.group(2) != null && !this.matcher.group(2).isEmpty();
	}
}
