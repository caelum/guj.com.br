package br.com.guj.feeds;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

public class FeedReader {
    public static Set<ItemIF> read(String url) {
        try {
        	System.out.println(new Date() + " - Lendo " + url);

            ChannelIF channel = FeedParser.parse(new ChannelBuilder(), url);
            return channel.getItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<ItemIF>();
    }
}
