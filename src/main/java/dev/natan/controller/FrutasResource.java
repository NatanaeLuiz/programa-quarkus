package dev.natan.controller;

import java.util.List;

import dev.natan.model.Fruta;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/frutas")
public class FrutasResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
        return Fruta.listAll();
    }

    @POST
    @Transactional
    public void novaFruta() {
        Fruta fruta = new Fruta();

        fruta.nome = "Maçã";
        fruta.qtd = 5;
        fruta.persist();
    }
}
