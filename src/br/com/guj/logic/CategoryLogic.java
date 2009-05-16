package br.com.guj.logic;

import javax.servlet.http.HttpServletRequest;

import org.vraptor.annotations.Component;
import org.vraptor.annotations.In;
import org.vraptor.annotations.Out;
import org.vraptor.i18n.Message;
import org.vraptor.validator.StringValidation;
import org.vraptor.validator.ValidationErrors;

import br.com.guj.hibernate.HibernateUtil;
import br.com.guj.model.Category;

@Component("category")
public class CategoryLogic {

	@Out
	private boolean isModerator;

	@In
	private HttpServletRequest request;

	public void save() {

		isModerator = true;

		// UserSession us = (UserSession) request.getAttribute("userSession");

	}

	public void add(Category category) {

		HibernateUtil.getSessionFactory().getCurrentSession().save(category);

	}

	protected Category getCategory(Long id) {
		return (Category) HibernateUtil.getSessionFactory().getCurrentSession()
				.get(Category.class, id);
	}

	public void validateAdd(ValidationErrors errors, Category category) {

		if (StringValidation.isBlank(category.getName())) {
			errors.add(new Message("category.name", "Nome;"));
		}

	}

}
