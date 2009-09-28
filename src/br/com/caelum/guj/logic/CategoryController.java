package br.com.caelum.guj.logic;

import static br.com.caelum.vraptor.view.Results.logic;

import org.hibernate.Session;
import org.vraptor.validator.StringValidation;

import br.com.caelum.guj.model.Category;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;

@Resource
public class CategoryController {

	private final Result result;
	private final Validator validator;
	private final Session session;

	public CategoryController(Result result, Validator validator, Session session) {
		this.result = result;
		this.validator = validator;
		this.session = session;
	}

	@Path("/categories") @Get
	public void save() {
		result.include("isModerator", true);
	}

	@Path("/categories") @Post
	public void add(final Category category) {
		validator.checking(new Validations() {{
			that(!StringValidation.isBlank(category.getName()),"category.name", "Nome;");
		}});
		validator.onErrorUse(logic()).forwardTo(CategoryController.class).save();

		session.save(category);
		result.use(logic()).redirectTo(CategoryController.class).save();
	}

	protected Category getCategory(Long id) {
		return (Category) session.get(Category.class, id);
	}

}
