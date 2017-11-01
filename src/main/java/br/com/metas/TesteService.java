package br.com.metas;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/rest")
public class TesteService {

	@GET
	public String testRest() {
		return "REST OK";
	}
	
}
