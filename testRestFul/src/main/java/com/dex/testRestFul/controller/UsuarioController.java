package com.dex.testRestFul.controller;

import com.dex.testRestFul.excepciones.NoSeHaEncontradoException;
import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuarioController{

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    @PostMapping("/create")
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
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioServiceImp.consultarPorId(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario);
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodosUsuarios() {
        List<Usuario> usuariosList = usuarioServiceImp.consultarTodosUsuarios();

        return ResponseEntity.ok(usuariosList);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> modificarUsuario(@RequestBody Usuario usuario) {
       Optional<Usuario> usuarioConsultado = usuarioServiceImp.consultarPorId(usuario.getId());

       if(usuarioConsultado.isPresent()){
           Usuario usuarioActualizado = usuario;
           usuarioServiceImp.insertarUsuario(usuarioActualizado);
           return ResponseEntity.ok(usuarioActualizado);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR NO SE ENCUENTRA EL USUARIO CONSULTADO");

       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) throws NoSeHaEncontradoException {
    usuarioServiceImp.eliminarUsuario(id);
    return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
    }




}
