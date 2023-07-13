package com.dex.testRestFul.controller;

import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController{

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioServiceImp.consultarPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {

        Optional<Usuario> usuarioConsultado = usuarioServiceImp.consultarPorId(usuario.getId());

        if (!usuarioConsultado.isPresent()) {

            Usuario usuarioGuardar = usuario;
            usuarioServiceImp.insertarUsuario(usuarioGuardar);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardar);



        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya existe");
        }


    }


}
