package br.com.caelum.guj.uri;

public interface URICache {

	public abstract String getBookmarkableURI(String compatibleURI);

	public abstract void put(String compatibleURI, String bookmarkableURI);
	
	public abstract void removeCache();

}