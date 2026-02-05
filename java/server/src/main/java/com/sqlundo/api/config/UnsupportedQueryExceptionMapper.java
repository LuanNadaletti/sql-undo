package com.sqlundo.api.config;

import com.sqlundo.functional.exception.UnsupportedQueryException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnsupportedQueryExceptionMapper implements ExceptionMapper<UnsupportedQueryException> {

    @Override
    public Response toResponse(UnsupportedQueryException exception) {
        ErrorResponse payload = new ErrorResponse(Status.NOT_IMPLEMENTED.getStatusCode(), "Unsupported query conversion");
        return Response.status(Status.NOT_IMPLEMENTED).entity(payload).build();
    }
}
