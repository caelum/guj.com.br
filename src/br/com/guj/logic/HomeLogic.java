package br.com.guj.logic;

import it.exprivia.cnos.opencloud.Tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.In;

import br.com.guj.Config;
import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.Post;
import br.com.guj.util.TagCloud;

/**
 * @author Rafael Steil
 */
@Component("home")
public class HomeLogic {

	private List<Post> posts;
	private List<Article> articles;
	private List<Tag> tags = new ArrayList<Tag>();

	@In
	private HttpServletRequest request;

	@SuppressWarnings("unchecked")
	private List<Post> getAllPosts() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Post p order by p.date desc").setMaxResults(
						Config.getIntvalue("posts.home.items")).setCacheable(
						true).setCacheRegion("homePosts").list();
	}

	@SuppressWarnings("unchecked")
	private List<Article> getAllArticles() {
		return HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery("from Article a order by a.id desc")
				.setMaxResults(Config.getIntvalue("article.home.items"))
				.setCacheable(true).setCacheRegion("homeArticles").list();
	}

	public void index() {

		this.posts = this.getAllPosts();
		this.articles = this.getAllArticles();

	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public List<it.exprivia.cnos.opencloud.Tag> getTags() {

		tags.addAll(new TagCloud().getTagCloud(this.request.getContextPath()));

		return tags;
	}

	public void setTags(List<it.exprivia.cnos.opencloud.Tag> tags) {
		this.tags = tags;
	}

}
