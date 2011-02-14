package br.com.caelum.guj.uri;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class DefaultURICache implements URICache {
	
	private CacheManager cacheManager;
	private Cache compatibleURI_bookmarkableURI;
	private static final String URIS = "uris";
	
	public DefaultURICache() {
		createCache();
	}

	private void createCache() {
		cacheManager = CacheManager.create();
		compatibleURI_bookmarkableURI = new Cache(URIS, 10000, false, false, 86400, 86400);
		cacheManager.addCache(compatibleURI_bookmarkableURI);
	}
	
	public void removeCache() {
		cacheManager.removeCache(URIS);
	}
	
	@Override
	public String getBookmarkableURI(String compatibleURI) {
		Element cachedElement = compatibleURI_bookmarkableURI.get(compatibleURI);
		if (cachedElement == null) {
			return null;
		}
		return (String) cachedElement.getValue();
	}
	
	@Override
	public void put(String compatibleURI, String bookmarkableURI) {
		this.compatibleURI_bookmarkableURI.put(new Element(compatibleURI, bookmarkableURI));
	}
}
