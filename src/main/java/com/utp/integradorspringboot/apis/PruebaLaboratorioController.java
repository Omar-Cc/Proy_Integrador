package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.PruebaLaboratorio;
import com.utp.integradorspringboot.repositories.PruebaLaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebas-laboratorio")
public class PruebaLaboratorioController {

    @Autowired
    private PruebaLaboratorioRepository pruebaLaboratorioRepository;

    @GetMapping
    public List<PruebaLaboratorio> getAllPruebasLaboratorio() {
        return pruebaLaboratorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaLaboratorio> getPruebaLaboratorioById(@PathVariable Integer id) {
        return pruebaLaboratorioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PruebaLaboratorio createPruebaLaboratorio(@RequestBody PruebaLaboratorio pruebaLaboratorio) {
        return pruebaLaboratorioRepository.save(pruebaLaboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PruebaLaboratorio> updatePruebaLaboratorio(@PathVariable Integer id, @RequestBody PruebaLaboratorio pruebaLaboratorio) {
        return pruebaLaboratorioRepository.findById(id)
                .map(existingPruebaLaboratorio -> {
                    pruebaLaboratorio.setId(existingPruebaLaboratorio.getId());
                    return ResponseEntity.ok(pruebaLaboratorioRepository.save(pruebaLaboratorio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePruebaLaboratorio(@PathVariable Integer id) {
        return pruebaLaboratorioRepository.findById(id)
                .map(pruebaLaboratorio -> {
                    pruebaLaboratorioRepository.delete(pruebaLaboratorio);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}