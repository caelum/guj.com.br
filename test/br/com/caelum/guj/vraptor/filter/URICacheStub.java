package br.com.caelum.guj.vraptor.filter;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.caelum.guj.uri.URICache;

public class URICacheStub implements URICache{
	
	private boolean getBookmarkableURICalled;
	private boolean putCalled;
	private boolean removeCacheCalled;
	private Map<String, String> cache = new LinkedHashMap<String, String>();
	
	public URICacheStub() {
		getBookmarkableURICalled = false;
		putCalled = false;
		removeCacheCalled = false;
	}

	@Override
	public String getBookmarkableURI(String compatibleURI) {
		getBookmarkableURICalled = true;
		return cache.get(compatibleURI);
	}

	@Override
	public void put(String compatibleURI, String bookmarkableURI) {
		putCalled = true;
		this.cache.put(compatibleURI, bookmarkableURI);
	}

	@Override
	public void removeCache() {
		removeCacheCalled = true;
		this.cache.clear();
	}

	public boolean isPutCalled() {
		return putCalled;
	}

	public boolean isRemoveCacheCalled() {
		return removeCacheCalled;
	}
	
	public boolean isGetBookmarkableURICalled() {
		return getBookmarkableURICalled;
	}
}
