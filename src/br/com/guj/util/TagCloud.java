package br.com.guj.util;

import it.exprivia.cnos.opencloud.Cloud;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Article;
import br.com.guj.model.Category;
import br.com.guj.model.Tag;

public class TagCloud {

	public List<it.exprivia.cnos.opencloud.Tag> getTagCloud(String contextPath) {

		Cloud cloud = new Cloud();

		cloud.setMaxTagsToDisplay(100);
		cloud.setMaxWeight(4.0);

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(1);

		List<Tag> tags = this.getAllTags();

		for (Tag tag : tags) {

			String link = contextPath + "/tag.showArticlesByTag.logic?id="
					+ tag.getId() + "&name=" + tag.getName();

			cloud
					.addTag(new it.exprivia.cnos.opencloud.Tag(tag.getName(),
							link));

		}

		return cloud.tags();
	}

	private List<Tag> getAllTags() {

		List<Category> allCategories = getAllCategories();

		List<Tag> tags = new ArrayList<Tag>();

		for (Category category : allCategories) {

			List<Article> articles = category.getArticles();

			for (Article article : articles) {

				tags.addAll(article.getTags());

			}

		}
		return tags;
	}

	@SuppressWarnings("unchecked")
	private static List<Category> getAllCategories() {
		return HibernateUtil.getSession().createQuery(
				"from Category c ORDER BY c.name").setCacheable(true)
				.setCacheRegion("Categories").list();
	}

}
