package br.com.caelum.guj.uri;

public class DefaultCompatibleURIBuilder implements CompatibleURIBuilder {
	
	@Override
	public String compatibleURL(String id) {
		return String.format("/posts/list/%s.java", id);
	}

	@Override
	public String compatibleURL(String id, int firstPostToShow) {
		return String.format("/posts/list/%d/%s.java", firstPostToShow, id);
	}

	@Override
	public String compatiblePrePostURL(String topicId, String postId) {
		return String.format("/posts/preList/%s/%s.java", topicId, postId);
	}
}
