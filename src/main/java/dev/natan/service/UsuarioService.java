package dev.natan.service;

import java.util.List;
import java.util.UUID;

import dev.natan.exception.UsuarioNotFoundException;
import dev.natan.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioService {
    
    public Usuario createUser(Usuario usuario) {
        Usuario.persist(usuario);
        return usuario;
    }

    public List<Usuario> findAll(Integer page, Integer size) {
       return Usuario.findAll()
                    .page(page, size)
                    .list();
    }

    public Usuario findById(UUID userID) {
        return (Usuario) Usuario.findByIdOptional(userID)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    public Object updateUser(UUID userID, Usuario usuarioAlterado) {
        
        var usuarioBanco = findById(userID);

        usuarioBanco.username = usuarioAlterado.username;

        Usuario.persist(usuarioBanco);
        return null;
    }

    public void deleteUserById(UUID userID) {

        var usuarioBanco = findById(userID);

        Usuario.deleteById(usuarioBanco.userId);
    }
}
