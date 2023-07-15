package com.dex.testRestFul.service;

import com.dex.testRestFul.excepciones.NoSeHaEncontradoException;
import com.dex.testRestFul.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public Usuario insertarUsuario(Usuario usuario);

    public Optional<Usuario> consultarPorId(Integer id);

    public List<Usuario> consultarTodosUsuarios();

    public Usuario modificarUsuario(Usuario usuario) throws NoSeHaEncontradoException;

    public void eliminarUsuario(Integer id) throws NoSeHaEncontradoException;

}
