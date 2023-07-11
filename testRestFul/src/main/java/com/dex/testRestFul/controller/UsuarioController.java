package com.dex.testRestFul.controller;

import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
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
    public ResponseEntity<?> crearUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuarioConsultado = usuarioService.obtenerUsuarioPorId(id);

        if (!usuarioConsultado.isPresent()) {
            Usuario nuevoUsuario = new Usuario(); // Crea una instancia de Usuario
            nuevoUsuario.setNombre("riki");
            nuevoUsuario.setApellido("platas");
            nuevoUsuario.setEdad(25);
            usuarioService.crearUsuario(nuevoUsuario); // Guarda el nuevo usuario en la base de datos
        }

        Optional<Usuario> usuarioCreado = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioCreado.isPresent()) {
            return ResponseEntity.ok(usuarioCreado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
