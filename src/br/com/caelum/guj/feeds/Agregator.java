package br.com.caelum.guj.feeds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.caelum.guj.Config;
import de.nava.informa.core.ItemIF;

public class Agregator {
	private final String intervalKey;
	private final String maxItemsKey;
	private final String feedUrl;
	private List<ItemIF> items = new ArrayList<ItemIF>();

	public Agregator(String intervalKey, String maxItemsKey, String feedUrl) {
		this.intervalKey = intervalKey;
		this.maxItemsKey = maxItemsKey;
		this.feedUrl = feedUrl;

		this.init();
	}

	public List<ItemIF> getItems() {
		return this.items;
	}

	private void init() {
		long interval = Config.getIntvalue(this.intervalKey) * 1000 * 60;

		Timer infoqTimer = new Timer(true);
		infoqTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					List<ItemIF> newFeeds = new ArrayList<ItemIF>(FeedReader.read(Config
							.getValue(Agregator.this.feedUrl)));

					int max = Config.getIntvalue(Agregator.this.maxItemsKey);

					if (newFeeds.size() > max) {
						newFeeds = newFeeds.subList(0, max);
					}

					if (!newFeeds.isEmpty()) {
						Agregator.this.items = newFeeds;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, new Date(), interval);
	}
}
