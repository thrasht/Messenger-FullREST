package org.montes.test.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("inyectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InyectDemoResource {
	
	@GET
	@Path("/annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("authSessionID") String header,
											@CookieParam("name") String cookie) {
		NewCookie c = new NewCookie("name", "Hello");
		
		
		Response.ok("OK").cookie(c).build();
		return "Matrix param: " + matrixParam + " Header param: " + header + " Cookie param: " + cookie;
	}
	
	@GET
	@Path("/context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		
	
		return "Path: " + uriInfo.getAbsolutePath() + " Cookies: " + headers.getCookies().toString();
	}

}
