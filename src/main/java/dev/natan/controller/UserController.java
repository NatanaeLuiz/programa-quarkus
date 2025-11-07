package dev.natan.controller;

import java.util.UUID;

import dev.natan.model.Usuario;
import dev.natan.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    
    private final UsuarioService userService;

    public UserController(UsuarioService userService) {
        this.userService = userService;
    }

    @POST
    @Transactional
    public Response createUser(Usuario usuario){
        return Response.ok(userService.createUser(usuario)).build();
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page, 
                            @QueryParam("size") @DefaultValue("10") Integer size){

        var users = userService.findAll(page, size);
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID userID){
        return Response.ok(userService.findById(userID)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateUser(@PathParam("id") UUID userID, Usuario usuario){
        return Response.ok(userService.updateUser(userID, usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") UUID userID){
        userService.deleteUserById(userID);
        return Response.noContent().build();
    }
}
