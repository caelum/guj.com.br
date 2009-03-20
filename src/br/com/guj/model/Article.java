package br.com.guj.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "articles")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String link;

	@Enumerated(EnumType.STRING)
	private ArticleLevel level;

	@Column(name = "created_at")
	private Date date;

	@ManyToMany
	@Cascade({CascadeType.ALL, CascadeType.DELETE_ORPHAN})
	@JoinTable(name = "article_tags")
	private List<Tag> tags = new ArrayList<Tag>();

	private boolean approved;
	private String subtitle;
	private boolean exclusive;
	private boolean oldStyle;
	private String content;
	private String pdf;
	private String download;
	private String author;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "author_email")
	private String authorEmail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public boolean isExclusive() {
		return exclusive;
	}

	public void setExclusive(boolean exclusive) {
		this.exclusive = exclusive;
	}

	public boolean isOldStyle() {
		return oldStyle;
	}

	public void setOldStyle(boolean oldStyle) {
		this.oldStyle = oldStyle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPdf() {
		return pdf;
	}

	public void setPdf(String pdf) {
		this.pdf = pdf;
	}

	public String getDownload() {
		return download;
	}

	public void setDownload(String download) {
		this.download = download;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getFormatedContent() {
		String contents = nl2br(this.content);
		contents = handleTitleTag(contents);
		return handleCreditsTag(contents);
	}

	private String nl2br(String s) {
		s = s.replaceAll("code>", "java>");
		Pattern p = Pattern.compile("(?m)<java>(.*?)<br/>(.*?)</java>");
		Matcher m = p.matcher(s);

		while (m.find()) {
			m = p.matcher(s);
			s = s.replaceAll("(?m)<java>(.*?)<br/>(.*?)</java>", "<java>$1<##replace##>$2</java>");
		}

		s = s.replaceAll("<##replace##>", "\n");
		s = s.replaceAll("<java>", "<br/> <textarea name='code' class='java' cols='60' rows='10'>");
		s = s.replaceAll("</java>", "</textarea> <br/>");

		return s;
	}

	private String handleTitleTag(String s) {
		return s.replaceAll("(?m)<title>(.*?)</title>", "<div id='subtitle'><b>$1</b></div>");
	}

	private String handleCreditsTag(String s) {
		return s.replaceAll("(?m)<credits>(.*?)</credits>", "<p>$1</b></p>");
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(ArticleLevel level) {
		this.level = level;
	}

	/**
	 * @return the level
	 */
	public ArticleLevel getLevel() {
		return level;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}
}