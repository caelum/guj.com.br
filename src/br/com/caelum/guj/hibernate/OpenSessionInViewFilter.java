package br.com.caelum.guj.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

/**
 * @author Rafael Steil
 */
public class OpenSessionInViewFilter implements Filter {
	private SessionFactory sf;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		try {
			sf.getCurrentSession().beginTransaction();

			chain.doFilter(request, response);

			sf.getCurrentSession().getTransaction().commit();
		}
		catch (Throwable ex) {
			ex.printStackTrace();
			try {
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
			}
			catch (Throwable rbEx) {}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}

	public void destroy() {
	}
}
