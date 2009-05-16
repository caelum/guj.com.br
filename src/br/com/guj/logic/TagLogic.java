package br.com.guj.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.Out;
import org.vraptor.annotations.Parameter;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;

@Component("tag")
public class TagLogic {

	private Set<Article> articles;

	@Out
	private String name;

	public void showArticlesByTag(@Parameter(key = "id") long id,
			@Parameter(key = "name") String name) {

		this.name = name;

		this.articles = new HashSet<Article>(listArticlesByTag(id));

	}

	@SuppressWarnings("unchecked")
	public List<Article> listArticlesByTag(long id) {

		return HibernateUtil.getSession().createQuery(
				"select a from Article a join a.tags t where t.id =:id")
				.setParameter("id", Long.valueOf(id).intValue()).list();
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
