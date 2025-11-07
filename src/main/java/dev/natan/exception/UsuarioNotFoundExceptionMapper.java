package dev.natan.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UsuarioNotFoundExceptionMapper implements ExceptionMapper<UsuarioNotFoundException>{

    @Override
    public Response toResponse(UsuarioNotFoundException exception) {

        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Usuario não existe").build();
    }
    
}
