package br.com.caelum.guj.logic;

import java.util.List;

import org.vraptor.annotations.Component;

import br.com.caelum.guj.feeds.JobsAgregator;
import de.nava.informa.core.ItemIF;

@Component("jobs")
public class JobsLogic {
	public void list() {

	}

	public List<ItemIF> getJobs() {
		return JobsAgregator.getItems();
	}
}
