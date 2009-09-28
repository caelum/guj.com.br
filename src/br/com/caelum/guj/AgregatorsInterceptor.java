package br.com.caelum.guj;

import javax.servlet.http.HttpSession;

import net.jforum.SessionFacade;
import net.jforum.entities.UserSession;
import net.jforum.util.preferences.ConfigKeys;
import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AgregatorsInterceptor implements Interceptor {
	private final Agregators agregators;
	private final Result result;
	private final HttpSession session;

	public AgregatorsInterceptor(Agregators agregators, Result result, HttpSession session) {
		this.agregators = agregators;
		this.result = result;
		this.session = session;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		agregators.addAggregators(result);

		boolean isLogged = "1".equals(session.getAttribute(ConfigKeys.LOGGED));
		result.include("logged", isLogged);

		if (isLogged) {
			UserSession userSession = SessionFacade.getUserSession(session.getId());
			result.include("userSession", userSession);
		}

		stack.next(method, instance);
	}
}
