package br.com.caelum.guj.uri.compatible;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import net.jforum.entities.Topic;

import org.junit.Test;

import br.com.caelum.guj.repositories.TopicRepository;
import br.com.caelum.guj.uri.BookmarkableURIBuilder;

public class CompatibleToBookmarkablePostConverterTest {
	
	@Test
	public void shouldIdentifyCompatibleURIs() {
		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(
				"/guj.com.br/posts/list/1.java", this.aRepository(), aBuilder());

		assertTrue(converter.isConvertable());

		converter = new CompatibleToBookmarkablePostConverter(
				"/recentTopics/list.java", this.aRepository(), aBuilder());

		assertFalse(converter.isConvertable());
	}

	@Test
	public void shouldIdentifyPaginatedURIs() {
		TopicRepository repository = this.aRepository();
		when(repository.getById(20)).thenReturn(aTopicCalled("eric created jforum"));
		
		BookmarkableURIBuilder builder = aBuilder();
		when(builder.bookmarkableURL(20, "eric created jforum", 4)).thenReturn("/java/20-erich-created-jforum/4");

		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(
				"/posts/list/45/20.java", repository, builder);

		assertEquals("/java/20-erich-created-jforum/4", converter.convert());

	}

	@Test
	public void shouldGenerateTheBookmarkableUrl() {
		TopicRepository repository = this.aRepository();
		when(repository.getById(20)).thenReturn(aTopicCalled("eric created jforum"));
		BookmarkableURIBuilder builder = aBuilder();
		when(builder.bookmarkableURL(20, "eric created jforum")).thenReturn("/java/20-erich-created-jforum");

		CompatibleToBookmarkablePostConverter converter = new CompatibleToBookmarkablePostConverter(
				"/posts/list/20.java", repository, builder);
		String bookmarkableURI = converter.convert();

		assertEquals("/java/20-erich-created-jforum", bookmarkableURI);
	}

	private TopicRepository aRepository() {
		return mock(TopicRepository.class);
	}
	
	private BookmarkableURIBuilder aBuilder() {
		return mock(BookmarkableURIBuilder.class);
	}
	
	private Topic aTopicCalled(String title) {
		Topic topic = new Topic();
		topic.setTitle(title);
		return topic;
	}
}
