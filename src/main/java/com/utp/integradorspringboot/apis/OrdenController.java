package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Orden;
import com.utp.integradorspringboot.repositories.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenRepository ordenRepository;

    @GetMapping
    public List<Orden> getAllOrdenes() {
        return ordenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> getOrdenById(@PathVariable Integer id) {
        return ordenRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Orden createOrden(@RequestBody Orden orden) {
        return ordenRepository.save(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> updateOrden(@PathVariable Integer id, @RequestBody Orden orden) {
        return ordenRepository.findById(id)
                .map(existingOrden -> {
                    orden.setId(existingOrden.getId());
                    return ResponseEntity.ok(ordenRepository.save(orden));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrden(@PathVariable Integer id) {
        return ordenRepository.findById(id)
                .map(orden -> {
                    ordenRepository.delete(orden);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}