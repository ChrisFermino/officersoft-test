package com.officersofttest.controllers;

import com.officersofttest.models.entities.Usuario;
import com.officersofttest.services.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.save(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> editUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok().body(usuarioService.edit(usuario));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<Page<Usuario>> findAll(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        return ResponseEntity.ok().body(usuarioService.findAll(pageNumber, pageSize));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        usuarioService.deleteByid(id);
        return ResponseEntity.ok().body("Registro<Usuario> excluido com sucesso");
    }
}
