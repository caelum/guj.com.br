package br.com.caelum.guj;

import org.ocpsoft.prettytime.PrettyTime;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class GlobalInterceptor implements Interceptor {
	private final Result result;
	private final Localization localization;

	public GlobalInterceptor(Result result, Localization localization) {
		this.result = result;
		this.localization = localization;
	}

	@Override
	public boolean accepts(ResourceMethod method) {
		return true;
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object instance) throws InterceptionException {
		result.include("prettyTimeFormatter", new PrettyTime(localization.getLocale()));
		stack.next(method, instance);
	}
}
