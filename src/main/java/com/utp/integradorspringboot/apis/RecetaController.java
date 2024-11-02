package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Receta;
import com.utp.integradorspringboot.repositories.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaRepository recetaRepository;

    @GetMapping
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> getRecetaById(@PathVariable Integer id) {
        return recetaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Receta createReceta(@RequestBody Receta receta) {
        return recetaRepository.save(receta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> updateReceta(@PathVariable Integer id, @RequestBody Receta receta) {
        return recetaRepository.findById(id)
                .map(existingReceta -> {
                    receta.setId(existingReceta.getId());
                    return ResponseEntity.ok(recetaRepository.save(receta));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceta(@PathVariable Integer id) {
        return recetaRepository.findById(id)
                .map(receta -> {
                    recetaRepository.delete(receta);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}