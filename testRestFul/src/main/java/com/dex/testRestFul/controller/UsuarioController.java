package com.dex.testRestFul.controller;

import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/api/usuarios")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioConsultado = usuarioService.obtenerUsuarioPorId(usuario.getId());

        if (!usuarioConsultado.isPresent()) {
            Usuario nuevoUsuario = new Usuario(); // Crea una instancia de Usuario
            nuevoUsuario.setNombre(usu);
            nuevoUsuario.setApellido("platas");
            nuevoUsuario.setEdad(25);
            usuarioService.crearUsuario(nuevoUsuario); // Guarda el nuevo usuario en la base de datos
        }

        Optional<Usuario> usuarioCreado = usuarioService.obtenerUsuarioPorId(usuario.getId());
        if (usuarioCreado.isPresent()) {
            return ResponseEntity.ok(usuarioCreado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
