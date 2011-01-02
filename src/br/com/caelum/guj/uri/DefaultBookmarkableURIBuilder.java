package br.com.caelum.guj.uri;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.caelum.guj.view.Slugger;

public class DefaultBookmarkableURIBuilder implements BookmarkableURIBuilder {

	private final Slugger slugger;

	public DefaultBookmarkableURIBuilder(Slugger slugger) {
		this.slugger = slugger;
	}

	@Override
	public String bookmarkableURL(int topicId, String title, int pageNumber) {
		return "/java/" + topicId + "-" + this.slugger.sluggerize(title) + "/" + pageNumber;
	}

	@Override
	public String bookmarkableURL(int topicId, String title, int pageNumber, String anchor) {
		return "/java/" + topicId + "-" + this.slugger.sluggerize(title) + "/" + pageNumber + "#" + anchor;
	}

	@Override
	public String bookmarkablePrePostURL(int topicId, int postId, String topicTitle) {
		return "/prepost/" + topicId + "/" + postId + "/" + this.slugger.sluggerize(topicTitle);
	}

	@Override
	public String bookmarkableURL(int topicId, String title) {
		return "/java/" + topicId + "-" + this.slugger.sluggerize(title);
	}

	@Override
	public String bookmarkableCompletePrePostURL(String originalURI, String topicTitle) {
		Map<String, String> infos = this.extractURIInformation(originalURI);
		if (!infos.isEmpty()) {
			return this.mountCompleteURI(topicTitle, infos);
		}

		return originalURI;
	}

	private String mountCompleteURI(String topicTitle, Map<String, String> infos) {
		int topicId = Integer.parseInt(infos.get("topicId"));
		int postId = Integer.parseInt(infos.get("postId"));

		return infos.get("baseURL") + this.bookmarkablePrePostURL(topicId, postId, topicTitle);
	}

	private Map<String, String> extractURIInformation(String completeURI) {
		Map<String, String> infos = new HashMap<String, String>();
		Pattern pattern = Pattern.compile("([\\S]+)/posts/preList/([0-9]+)/([0-9]+).java");

		Matcher matcher = pattern.matcher(completeURI);
		if (matcher.find()) {
			infos.put("topicId", matcher.group(2));
			infos.put("postId", matcher.group(3));
			infos.put("baseURL", matcher.group(1));
		}
		return infos;
	}
}
