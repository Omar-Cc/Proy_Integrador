package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.UsuarioRol;
import com.utp.integradorspringboot.repositories.UsuarioRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios-roles")
public class UsuarioRolController {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @GetMapping
    public List<UsuarioRol> getAllUsuariosRoles() {
        return usuarioRolRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRol> getUsuarioRolById(@PathVariable Integer id) {
        return usuarioRolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioRol createUsuarioRol(@RequestBody UsuarioRol usuarioRol) {
        return usuarioRolRepository.save(usuarioRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRol> updateUsuarioRol(@PathVariable Integer id, @RequestBody UsuarioRol usuarioRol) {
        return usuarioRolRepository.findById(id)
                .map(existingUsuarioRol -> {
                    usuarioRol.setId(existingUsuarioRol.getId());
                    return ResponseEntity.ok(usuarioRolRepository.save(usuarioRol));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioRol(@PathVariable Integer id) {
        return usuarioRolRepository.findById(id)
                .map(usuarioRol -> {
                    usuarioRolRepository.delete(usuarioRol);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}