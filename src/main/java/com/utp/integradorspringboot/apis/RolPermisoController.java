package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.RolPermiso;
import com.utp.integradorspringboot.repositories.RolPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles-permisos")
public class RolPermisoController {

    @Autowired
    private RolPermisoRepository rolPermisoRepository;

    @GetMapping
    public List<RolPermiso> getAllRolesPermisos() {
        return rolPermisoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolPermiso> getRolPermisoById(@PathVariable Integer id) {
        return rolPermisoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RolPermiso createRolPermiso(@RequestBody RolPermiso rolPermiso) {
        return rolPermisoRepository.save(rolPermiso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolPermiso> updateRolPermiso(@PathVariable Integer id, @RequestBody RolPermiso rolPermiso) {
        return rolPermisoRepository.findById(id)
                .map(existingRolPermiso -> {
                    rolPermiso.setId(existingRolPermiso.getId());
                    return ResponseEntity.ok(rolPermisoRepository.save(rolPermiso));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRolPermiso(@PathVariable Integer id) {
        return rolPermisoRepository.findById(id)
                .map(rolPermiso -> {
                    rolPermisoRepository.delete(rolPermiso);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}