package br.com.caelum.guj.feeds;

import java.util.Date;



public class TopicJsonFeed {
	private int id;
	private String title;
	private String uri;
	private int views;
	private int replies;
	private String author;
	private transient int authorId;
	private Date date;

	public int getId() {
		return id;
	}
	public void setId(int topicId) {
		this.id = topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String topicTitle) {
		this.title = topicTitle;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String topicURI) {
		this.uri = topicURI;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String topicAuthor) {
		this.author = topicAuthor;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date topicDate) {
		this.date = topicDate;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getReplies() {
		return replies;
	}
	public void setReplies(int replies) {
		this.replies = replies;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}
