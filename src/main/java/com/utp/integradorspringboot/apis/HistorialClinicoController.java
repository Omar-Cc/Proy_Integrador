package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.HistorialClinico;
import com.utp.integradorspringboot.repositories.HistorialClinicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-clinicos")
public class HistorialClinicoController {

    @Autowired
    private HistorialClinicoRepository historialClinicoRepository;

    @GetMapping
    public List<HistorialClinico> getAllHistorialClinicos() {
        return historialClinicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> getHistorialClinicoById(@PathVariable Integer id) {
        return historialClinicoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HistorialClinico createHistorialClinico(@RequestBody HistorialClinico historialClinico) {
        return historialClinicoRepository.save(historialClinico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialClinico> updateHistorialClinico(@PathVariable Integer id, @RequestBody HistorialClinico historialClinico) {
        return historialClinicoRepository.findById(id)
                .map(existingHistorialClinico -> {
                    historialClinico.setId(existingHistorialClinico.getId());
                    return ResponseEntity.ok(historialClinicoRepository.save(historialClinico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHistorialClinico(@PathVariable Integer id) {
        return historialClinicoRepository.findById(id)
                .map(historialClinico -> {
                    historialClinicoRepository.delete(historialClinico);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}