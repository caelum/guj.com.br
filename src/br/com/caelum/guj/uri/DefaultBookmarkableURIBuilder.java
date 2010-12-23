package br.com.caelum.guj.uri;

import br.com.caelum.guj.view.Slugger;

public class DefaultBookmarkableURIBuilder implements BookmarkableURIBuilder {

	private final Slugger slugger;

	public DefaultBookmarkableURIBuilder(Slugger slugger) {
		this.slugger = slugger;
	}

	@Override
	public String bookmarkableURL(int topicId, String title, int pageNumber) {
		return "/java/" + topicId + "-" + slugger.sluggerize(title) + "/" + pageNumber;
	}
	
	@Override
	public String bookmarkableURL(int topicId, String title, int pageNumber, String anchor) {
		return "/java/" + topicId + "-" + slugger.sluggerize(title) + "/" + pageNumber + "#" + anchor;
	}

	@Override
	public String bookmarkablePrePostURL(int topicId, int postId, String topicTitle) {
		return "/prepost/"+ topicId +"/"+ postId +"/" + slugger.sluggerize(topicTitle) ;
	}
	
	@Override
	public String bookmarkableURL(int topicId, String title) {
		return "/java/" + topicId + "-" + slugger.sluggerize(title);
	}
}
