package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Muestra;
import com.utp.integradorspringboot.repositories.MuestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/muestras")
public class MuestraController {

    @Autowired
    private MuestraRepository muestraRepository;

    @GetMapping
    public List<Muestra> getAllMuestras() {
        return muestraRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Muestra> getMuestraById(@PathVariable Integer id) {
        return muestraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Muestra createMuestra(@RequestBody Muestra muestra) {
        return muestraRepository.save(muestra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Muestra> updateMuestra(@PathVariable Integer id, @RequestBody Muestra muestra) {
        return muestraRepository.findById(id)
                .map(existingMuestra -> {
                    muestra.setId(existingMuestra.getId());
                    return ResponseEntity.ok(muestraRepository.save(muestra));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMuestra(@PathVariable Integer id) {
        return muestraRepository.findById(id)
                .map(muestra -> {
                    muestraRepository.delete(muestra);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}