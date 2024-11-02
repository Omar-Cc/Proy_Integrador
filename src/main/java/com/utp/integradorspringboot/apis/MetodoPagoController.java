package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.MetodoPago;
import com.utp.integradorspringboot.repositories.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @GetMapping
    public List<MetodoPago> getAllMetodosPago() {
        return metodoPagoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> getMetodoPagoById(@PathVariable Integer id) {
        return metodoPagoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MetodoPago createMetodoPago(@RequestBody MetodoPago metodoPago) {
        return metodoPagoRepository.save(metodoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> updateMetodoPago(@PathVariable Integer id, @RequestBody MetodoPago metodoPago) {
        return metodoPagoRepository.findById(id)
                .map(existingMetodoPago -> {
                    metodoPago.setId(existingMetodoPago.getId());
                    return ResponseEntity.ok(metodoPagoRepository.save(metodoPago));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMetodoPago(@PathVariable Integer id) {
        return metodoPagoRepository.findById(id)
                .map(metodoPago -> {
                    metodoPagoRepository.delete(metodoPago);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}