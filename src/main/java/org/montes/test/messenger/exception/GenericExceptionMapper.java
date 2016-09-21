package org.montes.test.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.montes.test.messenger.model.ErrorMessage;

//@Provider se elimina para usar WebApplicationException de jax rs
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorMessage error = new ErrorMessage(exception.getMessage(), 500, "http://tuputamadre.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}
}
