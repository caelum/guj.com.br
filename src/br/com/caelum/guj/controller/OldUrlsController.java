package br.com.caelum.guj.controller;

import static br.com.caelum.vraptor.view.Results.http;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

/**
 * Esse controller apenas redireciona as urls antigas para as novas
 * 
 * @author Lucas Cavalcanti
 * 
 */
@Resource
public class OldUrlsController {

	private final Result result;

	public OldUrlsController(Result result) {
		this.result = result;
	}

	@Path("/home.index.logic")
	public void home() {
		result.use(http()).movedPermanentlyTo(HomeController.class).index();
	}

	@Path("/article.list.logic")
	public void articlesList() {
		result.use(http()).movedPermanentlyTo(ArticleController.class).list();
	}

	@Path("/jobs.list.logic")
	public void jobsList() {
		result.use(http()).movedPermanentlyTo(JobsController.class).list();
	}

	@Path("/article.show.logic")
	public void articleShow(long id) {
		result.use(http()).movedPermanentlyTo(ArticleController.class).show(id);
	}

	@Path("/article.listByTag.logic")
	public void articleListByTag(String tag) {
		result.use(http()).movedPermanentlyTo(ArticleController.class).listByTag(tag);
	}

	@Path("/noticias")
	public void noticias() {
		result.use(http()).movedPermanentlyTo("/forums/show/17.java");
	}
	
	@Path("/{id}")
	public void shortTopicURL(long id){
		result.use(Results.http()).movedPermanentlyTo("/java/"+id);
	}
	
	
	@Path("/MundoJ")
	public void mundoJTopicURL() {
		result.use(http()).movedPermanentlyTo("/forums/show/25.java");
	}
}
