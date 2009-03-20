package br.com.guj.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author Rafael Steil
 */
public class HibernateUtil {
	private static SessionFactory factory;

	static {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure();
		factory = configuration.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static Session getSession() {
		return getSessionFactory().getCurrentSession();
	}
}
