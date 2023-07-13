package com.dex.testRestFul.service;

import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
