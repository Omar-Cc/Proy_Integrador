package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Permiso;
import com.utp.integradorspringboot.repositories.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
public class PermisoController {

    @Autowired
    private PermisoRepository permisoRepository;

    @GetMapping
    public List<Permiso> getAllPermisos() {
        return permisoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permiso> getPermisoById(@PathVariable Integer id) {
        return permisoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Permiso createPermiso(@RequestBody Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permiso> updatePermiso(@PathVariable Integer id, @RequestBody Permiso permiso) {
        return permisoRepository.findById(id)
                .map(existingPermiso -> {
                    permiso.setId(existingPermiso.getId());
                    return ResponseEntity.ok(permisoRepository.save(permiso));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermiso(@PathVariable Integer id) {
        return permisoRepository.findById(id)
                .map(permiso -> {
                    permisoRepository.delete(permiso);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}