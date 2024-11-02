package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Costo;
import com.utp.integradorspringboot.repositories.CostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/costos")
public class CostoController {

    @Autowired
    private CostoRepository costoRepository;

    @GetMapping
    public List<Costo> getAllCostos() {
        return costoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Costo> getCostoById(@PathVariable Integer id) {
        return costoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Costo createCosto(@RequestBody Costo costo) {
        return costoRepository.save(costo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Costo> updateCosto(@PathVariable Integer id, @RequestBody Costo costoDetails) {
        return costoRepository.findById(id)
                .map(costo -> {
                    costo.setCosto(costoDetails.getCosto());
                    costo.setEspecialidad(costoDetails.getEspecialidad());
                    return ResponseEntity.ok(costoRepository.save(costo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCosto(@PathVariable Integer id) {
        return costoRepository.findById(id)
                .map(costo -> {
                    costoRepository.delete(costo);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}