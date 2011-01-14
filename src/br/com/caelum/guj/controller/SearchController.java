package br.com.caelum.guj.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class SearchController {

	@Get
	@Path("/search")
	public void search() {
	}
}
