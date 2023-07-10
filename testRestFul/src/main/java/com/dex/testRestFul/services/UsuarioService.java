package com.dex.testRestFul.services;

import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

 @Autowired
    UsuarioRepository usuarioRepository;

    public void crearUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerUsuarioPorId(int id) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(id);
         if(usuarioBuscado!= null){
             return usuarioBuscado;
         }else {
             return null;
         }

    }

}
