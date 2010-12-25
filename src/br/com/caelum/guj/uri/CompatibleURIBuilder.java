package br.com.caelum.guj.uri;

public interface CompatibleURIBuilder {

	String compatibleURL(String id);

	String compatibleURL(String id, int firstPostToShow);

	String compatiblePrePostURL(String topicId, String postId);

}