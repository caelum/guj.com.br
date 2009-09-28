package br.com.caelum.guj.logic;

import br.com.caelum.guj.feeds.JobsAgregator;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class JobsController {

	private final Result result;

	public JobsController(Result result) {
		this.result = result;
	}

	@Path("/jobs")
	public void list() {
		result.include("jobs", JobsAgregator.getItems());
	}

}
