package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Ubigeo;
import com.utp.integradorspringboot.repositories.UbigeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubigeos")
public class UbigeoController {

    @Autowired
    private UbigeoRepository ubigeoRepository;

    @GetMapping
    public List<Ubigeo> getAllUbigeos() {
        return ubigeoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ubigeo> getUbigeoById(@PathVariable Integer id) {
        return ubigeoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ubigeo createUbigeo(@RequestBody Ubigeo ubigeo) {
        return ubigeoRepository.save(ubigeo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ubigeo> updateUbigeo(@PathVariable Integer id, @RequestBody Ubigeo ubigeo) {
        return ubigeoRepository.findById(id)
                .map(existingUbigeo -> {
                    ubigeo.setId(existingUbigeo.getId());
                    return ResponseEntity.ok(ubigeoRepository.save(ubigeo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbigeo(@PathVariable Integer id) {
        return ubigeoRepository.findById(id)
                .map(ubigeo -> {
                    ubigeoRepository.delete(ubigeo);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}