package br.com.caelum.guj.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.guj.model.NewsletterParticipant;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;

@Component
@RequestScoped
public class GUJUserDAO {
	private final Session session;

	public GUJUserDAO(Session session) {
		this.session = session;
	}

	public String emailFromUser(Integer gujUserId) {
		SQLQuery query = this.session.createSQLQuery("select user_email from jforum_users where user_id = :pUserId");
		query.setParameter("pUserId", gujUserId);
		String email = (String) query.uniqueResult();
		return email;
	}

	public void registerNewsletterParticipant(NewsletterParticipant p) {
		this.session.save(p);
	}

	public boolean existParticipant(NewsletterParticipant p) {
		Criteria criteria = this.session.createCriteria(NewsletterParticipant.class).add(
				Restrictions.eq("gujUserId", p.getGujUserId()));
		NewsletterParticipant participant = (NewsletterParticipant) criteria.uniqueResult();

		return participant != null;
	}

	public NewsletterParticipant findParticipantByGujUserId(Integer gujUserId) {
		return (NewsletterParticipant) this.session.get(NewsletterParticipant.class, gujUserId);
	}

	public void unregisterNewsletterParticipant(NewsletterParticipant participant) {
		this.session.delete(participant);
	}
}
