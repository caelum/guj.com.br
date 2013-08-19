package br.com.caelum.guj;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import freemarker.template.SimpleHash;

import br.com.caelum.guj.feeds.Agregator;
import br.com.caelum.guj.feeds.JobsAgregator;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class Agregators {
	private HashMap<String, Agregator> agregatorsMap = new HashMap<String, Agregator>();
	private static Agregators instance;

	@PostConstruct
	public void init() {
		Config.loadConfigs();

		agregatorsMap.put("infoq", new Agregator("infoq.refresh.interval", "infoq.items", "infoq.url"));
		agregatorsMap.put("forum", new Agregator("forum.refresh.interval", "forum.items", "forum.url"));
		agregatorsMap.put("news", new Agregator("news.refresh.interval", "news.items", "news.url"));
		agregatorsMap.put("brutalQuestions", new Agregator("brutal.refresh.interval", "brutal.items", "brutal.url"));
		agregatorsMap.put("brutalNews", new Agregator("brutal_news.refresh.interval", "brutal_news.items", "brutal_news.url"));

		JobsAgregator.start();
		
		instance = this;
	}
	
	public static Agregators getInstance(){
		return instance;
	}

	public void addAggregators(Result result) {
		Set<Entry<String, Agregator>> entrySet = agregatorsMap.entrySet();
		for (Entry<String, Agregator> entry : entrySet) {
			result.include(entry.getKey(), entry.getValue().getItems());
		}
	}
	
	public void addAggregators(SimpleHash context) {
		Set<Entry<String, Agregator>> entrySet = agregatorsMap.entrySet();
		for (Entry<String, Agregator> entry : entrySet) {
			context.put(entry.getKey(), entry.getValue().getItems());
		}
	}
}
