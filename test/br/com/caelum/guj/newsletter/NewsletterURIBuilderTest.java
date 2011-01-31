package br.com.caelum.guj.newsletter;

import junit.framework.Assert;

import org.junit.Test;
import org.mockito.Mockito;

public class NewsletterURIBuilderTest {

	@Test
	public void shouldReplaceAllPlaceholdersWhenBuildingTheURI() {
		NewsletterConfigs configs = Mockito.mock(NewsletterConfigs.class);
		Mockito.when(configs.getAPIKey()).thenReturn("anApiKey");
		Mockito.when(configs.getDc()).thenReturn("aDc");
		Mockito.when(configs.getListId()).thenReturn("aListId");
		NewsletterURIBuilder builder = new NewsletterURIBuilder(configs);
		String generated = builder.emailAddress("john@doe.com").optin(true).genereateURI();

		String expected = "http://aDc.api.mailchimp.com/1.3/?output=xml&method=listSubscribe&apikey=anApiKey&email_address=john@doe.com&double_optin=true&id=aListId";
		Assert.assertEquals(expected, generated);
	}
}
