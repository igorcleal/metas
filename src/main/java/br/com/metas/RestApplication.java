package br.com.metas;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new TesteService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
