package br.com.caelum.guj.uri;

public interface BookmarkableURIBuilder {

	String bookmarkableURL(int topicId, String title, int pageNumber);

	String bookmarkableURL(int topicId, String title);

	String bookmarkableURL(int topicId, String title, int pageNumber, String anchor);

	String bookmarkablePrePostURL(int topicId, int postId, String topicTitle);

	String bookmarkableCompletePrePostURL(String originalURI, String topicTitle);
}