package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.OrdenLaboratorio;
import com.utp.integradorspringboot.repositories.OrdenLaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes-laboratorio")
public class OrdenLaboratorioController {

    @Autowired
    private OrdenLaboratorioRepository ordenLaboratorioRepository;

    @GetMapping
    public List<OrdenLaboratorio> getAllOrdenesLaboratorio() {
        return ordenLaboratorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenLaboratorio> getOrdenLaboratorioById(@PathVariable Integer id) {
        return ordenLaboratorioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrdenLaboratorio createOrdenLaboratorio(@RequestBody OrdenLaboratorio ordenLaboratorio) {
        return ordenLaboratorioRepository.save(ordenLaboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenLaboratorio> updateOrdenLaboratorio(@PathVariable Integer id, @RequestBody OrdenLaboratorio ordenLaboratorio) {
        return ordenLaboratorioRepository.findById(id)
                .map(existingOrdenLaboratorio -> {
                    ordenLaboratorio.setId(existingOrdenLaboratorio.getId());
                    return ResponseEntity.ok(ordenLaboratorioRepository.save(ordenLaboratorio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdenLaboratorio(@PathVariable Integer id) {
        return ordenLaboratorioRepository.findById(id)
                .map(ordenLaboratorio -> {
                    ordenLaboratorioRepository.delete(ordenLaboratorio);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}