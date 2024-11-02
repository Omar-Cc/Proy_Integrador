package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Estado;
import com.utp.integradorspringboot.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> getAllEstados() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> getEstadoById(@PathVariable Integer id) {
        return estadoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Estado createEstado(@RequestBody Estado estado) {
        return estadoRepository.save(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> updateEstado(@PathVariable Integer id, @RequestBody Estado estado) {
        return estadoRepository.findById(id)
                .map(existingEstado -> {
                    estado.setId(existingEstado.getId());
                    return ResponseEntity.ok(estadoRepository.save(estado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Integer id) {
        return estadoRepository.findById(id)
                .map(estado -> {
                    estadoRepository.delete(estado);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}