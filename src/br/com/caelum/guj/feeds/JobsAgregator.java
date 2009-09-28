package br.com.caelum.guj.feeds;

import java.util.List;

import de.nava.informa.core.ItemIF;

public class JobsAgregator {
	private static Agregator agregator;

	public static void start() {
		agregator = new Agregator("jobs.refresh.interval", "jobs.items", "jobs.url");
	}

	public static List<ItemIF> getItems() {
		return agregator.getItems();
	}
}
