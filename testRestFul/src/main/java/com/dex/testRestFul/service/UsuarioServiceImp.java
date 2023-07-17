package com.dex.testRestFul.service;

import com.dex.testRestFul.excepciones.NoSeHaEncontradoException;
import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService{

 @Autowired
    UsuarioRepository usuarioRepository;


    @Override
    public Usuario insertarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> consultarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> consultarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) throws NoSeHaEncontradoException {
        if(usuarioRepository.existsById(usuario.getId())){
            return usuarioRepository.save(usuario);
        }else{
            throw new NoSeHaEncontradoException("No se ha encontrado el usuario");
        }
    }

    @Override
    public void eliminarUsuario(Integer id) {
        try {
            if (usuarioRepository.findById(id).isPresent()) {
                usuarioRepository.deleteById(id);
            } else {
                throw new NoSeHaEncontradoException("No existe el usuario");
            }
        } catch (NoSeHaEncontradoException e) {
            // Manejar la excepción aquí o relanzarla si es necesario
            e.printStackTrace(); // Imprimir el rastro de la excepción
        }
    }

}
