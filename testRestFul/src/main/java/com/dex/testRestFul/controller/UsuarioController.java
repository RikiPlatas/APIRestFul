package com.dex.testRestFul.controller;

import com.dex.testRestFul.dto.Mapper;
import com.dex.testRestFul.dto.UsuarioDTO;
import com.dex.testRestFul.excepciones.NoSeHaEncontradoException;
import com.dex.testRestFul.model.Usuario;
import com.dex.testRestFul.service.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsuarioController{

    @Autowired
    private UsuarioServiceImp usuarioServiceImp;
    private final Mapper<Usuario, UsuarioDTO> mapper;

    public UsuarioController(UsuarioServiceImp usuarioServiceImp, Mapper<Usuario, UsuarioDTO> mapper) {
        this.usuarioServiceImp = usuarioServiceImp;
        this.mapper = mapper;
    }

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
    public ResponseEntity<?> obtenerUsuario(@PathVariable int id) throws IllegalAccessException, InstantiationException {
        Optional<Usuario> usuario = usuarioServiceImp.consultarPorId(id);
        if (usuario.isPresent()) {
            UsuarioDTO usuarioDTO = mapper.map(usuario.get(),UsuarioDTO.class);
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<?> obtenerTodosUsuarios() throws IllegalAccessException, InstantiationException {
        List<Usuario> usuariosList = usuarioServiceImp.consultarTodosUsuarios();

        List<UsuarioDTO> usuariosDTO = new ArrayList<>();
        for (Usuario usuario : usuariosList) {
            UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
            usuariosDTO.add(usuarioDTO);
        }

        return ResponseEntity.ok(usuariosDTO);
    }

    @PutMapping("/edit")
    public ResponseEntity<?> modificarUsuario(@RequestBody Usuario usuario) {
       Optional<Usuario> usuarioConsultado = usuarioServiceImp.consultarPorId(usuario.getId());

       if(usuarioConsultado.isPresent()){
           Usuario usuarioActualizado = usuario;
           usuarioServiceImp.insertarUsuario(usuarioActualizado);
           return ResponseEntity.ok(usuarioActualizado);
       }else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR NO SE ENCUENTRA EL USUARIO");

       }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable int id) throws NoSeHaEncontradoException {
    usuarioServiceImp.eliminarUsuario(id);
    return ResponseEntity.status(HttpStatus.OK).body("Usuario eliminado correctamente");
    }




}
