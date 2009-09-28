package br.com.caelum.guj;

import javax.annotation.PostConstruct;

import br.com.caelum.guj.feeds.Agregator;
import br.com.caelum.guj.feeds.JobsAgregator;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class Agregators {
	private Agregator forumAgregator;
	private Agregator newsAgregator;
	private Agregator infoqAgregator;

	@PostConstruct
	public void init() {
		Config.loadConfigs();

		this.forumAgregator = new Agregator("forum.refresh.interval",
				"forum.items", "forum.url");
		this.newsAgregator = new Agregator("news.refresh.interval",
				"news.items", "news.url");
		this.infoqAgregator = new Agregator("infoq.refresh.interval",
				"infoq.items", "infoq.url");

		JobsAgregator.start();
	}

	public void addAggregators(Result result) {
		result.include("infoq", this.infoqAgregator.getItems());
		result.include("news", this.newsAgregator.getItems());
		result.include("forum", this.forumAgregator.getItems());
		result.include("jobs", JobsAgregator.getItems());
	}
}
