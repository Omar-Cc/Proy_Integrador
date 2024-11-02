package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.DetalleFactura;
import com.utp.integradorspringboot.repositories.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-facturas")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @GetMapping
    public List<DetalleFactura> getAllDetalleFacturas() {
        return detalleFacturaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> getDetalleFacturaById(@PathVariable Integer id) {
        return detalleFacturaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleFactura createDetalleFactura(@RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> updateDetalleFactura(@PathVariable Integer id, @RequestBody DetalleFactura detalleFacturaDetails) {
        return detalleFacturaRepository.findById(id)
                .map(detalleFactura -> {
                    detalleFactura.setFactura(detalleFacturaDetails.getFactura());
                    detalleFactura.setDescripcion(detalleFacturaDetails.getDescripcion());
                    detalleFactura.setCantidad(detalleFacturaDetails.getCantidad());
                    detalleFactura.setPrecioUnitario(detalleFacturaDetails.getPrecioUnitario());
                    detalleFactura.setSubtotal(detalleFacturaDetails.getSubtotal());
                    return ResponseEntity.ok(detalleFacturaRepository.save(detalleFactura));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleFactura(@PathVariable Integer id) {
        return detalleFacturaRepository.findById(id)
                .map(detalleFactura -> {
                    detalleFacturaRepository.delete(detalleFactura);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}