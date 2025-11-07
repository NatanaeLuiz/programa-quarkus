package dev.natan.service;

import java.util.List;
import java.util.UUID;

import dev.natan.exception.UsuarioNotFoundException;
import dev.natan.model.Usuario;
import dev.natan.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioService {
    
    private final UsuarioRepository userRepository;

    
    public UsuarioService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuario createUser(Usuario usuario) {
        userRepository.persist(usuario);
        return usuario;
    }

    public List<Usuario> findAll(Integer page, Integer size) {
       return userRepository.findAll()
                    .page(page, size)
                    .list();
    }

    public Usuario findById(UUID userID) {
        return (Usuario) userRepository.findByIdOptional(userID)
                .orElseThrow(UsuarioNotFoundException::new);
    }

    public Object updateUser(UUID userID, Usuario usuarioAlterado) {
        
        var usuarioBanco = findById(userID);

        usuarioBanco.setUsername(usuarioAlterado.getUsername());

        userRepository.persist(usuarioBanco);
        return null;
    }

    public void deleteUserById(UUID userID) {

        var usuarioBanco = findById(userID);

        userRepository.deleteById(usuarioBanco.getUserId());
    }
}
