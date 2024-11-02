package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Factura;
import com.utp.integradorspringboot.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaRepository facturaRepository;

    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Integer id) {
        return facturaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaRepository.save(factura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Integer id, @RequestBody Factura factura) {
        return facturaRepository.findById(id)
                .map(existingFactura -> {
                    factura.setId(existingFactura.getId());
                    return ResponseEntity.ok(facturaRepository.save(factura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Integer id) {
        return facturaRepository.findById(id)
                .map(factura -> {
                    facturaRepository.delete(factura);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}