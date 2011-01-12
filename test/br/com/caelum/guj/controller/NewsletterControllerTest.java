package br.com.caelum.guj.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

import br.com.caelum.guj.dao.GUJUserDAO;
import br.com.caelum.guj.model.NewsletterParticipant;

public class NewsletterControllerTest {

	@Test
	public void should_register_the_given_user_if_its_not_a_participant_yet() {
		Integer gujUserId = 1;
		GUJUserDAO dao = mock(GUJUserDAO.class);

		when(dao.findParticipantByGujUserId(gujUserId)).thenReturn(null);
		when(dao.emailFromUser(gujUserId)).thenReturn("email@email.com");

		NewsletterController controller = new NewsletterController(dao);
		controller.register(gujUserId);

		verify(dao).registerNewsletterParticipant(any(NewsletterParticipant.class));
	}

	@Test
	public void should_not_register_the_given_user_if_its_already_a_participant() {
		Integer gujUserId = 1;

		GUJUserDAO dao = mock(GUJUserDAO.class);

		when(dao.findParticipantByGujUserId(gujUserId)).thenReturn(new NewsletterParticipant());

		NewsletterController controller = new NewsletterController(dao);
		controller.register(gujUserId);

		verify(dao, never()).registerNewsletterParticipant(any(NewsletterParticipant.class));
	}

	@Test
	public void should_unregister_if_its_already_a_registered_participant() {
		Integer gujUserId = 1;
		GUJUserDAO dao = mock(GUJUserDAO.class);

		when(dao.findParticipantByGujUserId(gujUserId)).thenReturn(new NewsletterParticipant());

		NewsletterController controller = new NewsletterController(dao);
		controller.unregister(gujUserId);

		verify(dao).unregisterNewsletterParticipant(any(NewsletterParticipant.class));
	}

	@Test
	public void should_not_unregister_if_its_already_a_registered_participant() {
		Integer gujUserId = 1;
		GUJUserDAO dao = mock(GUJUserDAO.class);

		when(dao.findParticipantByGujUserId(gujUserId)).thenReturn(null);

		NewsletterController controller = new NewsletterController(dao);
		controller.unregister(gujUserId);

		verify(dao, never()).unregisterNewsletterParticipant(any(NewsletterParticipant.class));
	}
}
