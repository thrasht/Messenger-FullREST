package org.montes.test.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.montes.test.messenger.model.ErrorMessage;


@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		ErrorMessage error = new ErrorMessage(exception.getMessage(), 404, "http://tuputamadre.com");
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}

}
