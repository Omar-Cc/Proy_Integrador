package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.DetalleReceta;
import com.utp.integradorspringboot.repositories.DetalleRecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-recetas")
public class DetalleRecetaController {

    @Autowired
    private DetalleRecetaRepository detalleRecetaRepository;

    @GetMapping
    public List<DetalleReceta> getAllDetalleRecetas() {
        return detalleRecetaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleReceta> getDetalleRecetaById(@PathVariable Integer id) {
        return detalleRecetaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleReceta createDetalleReceta(@RequestBody DetalleReceta detalleReceta) {
        return detalleRecetaRepository.save(detalleReceta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleReceta> updateDetalleReceta(@PathVariable Integer id, @RequestBody DetalleReceta detalleReceta) {
        return detalleRecetaRepository.findById(id)
                .map(existingDetalleReceta -> {
                    detalleReceta.setId(existingDetalleReceta.getId());
                    return ResponseEntity.ok(detalleRecetaRepository.save(detalleReceta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleReceta(@PathVariable Integer id) {
        return detalleRecetaRepository.findById(id)
                .map(detalleReceta -> {
                    detalleRecetaRepository.delete(detalleReceta);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}