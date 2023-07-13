package com.dex.testRestFul.service;

import com.dex.testRestFul.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    public Usuario insertarUsuario(Usuario usuario);

    public Optional<Usuario> consultarPorId(Integer id);

}
