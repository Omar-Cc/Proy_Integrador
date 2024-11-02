package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.DetalleOrden;
import com.utp.integradorspringboot.repositories.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-ordenes")
public class DetalleOrdenController {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @GetMapping
    public List<DetalleOrden> getAllDetalleOrdenes() {
        return detalleOrdenRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrden> getDetalleOrdenById(@PathVariable Integer id) {
        return detalleOrdenRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleOrden createDetalleOrden(@RequestBody DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrden> updateDetalleOrden(@PathVariable Integer id, @RequestBody DetalleOrden detalleOrdenDetails) {
        return detalleOrdenRepository.findById(id)
                .map(detalleOrden -> {
                    detalleOrden.setOrden(detalleOrdenDetails.getOrden());
                    detalleOrden.setMedicamento(detalleOrdenDetails.getMedicamento());
                    detalleOrden.setCantidad(detalleOrdenDetails.getCantidad());
                    detalleOrden.setPrecioUnitario(detalleOrdenDetails.getPrecioUnitario());
                    detalleOrden.setSubtotal(detalleOrdenDetails.getSubtotal());
                    return ResponseEntity.ok(detalleOrdenRepository.save(detalleOrden));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleOrden(@PathVariable Integer id) {
        return detalleOrdenRepository.findById(id)
                .map(detalleOrden -> {
                    detalleOrdenRepository.delete(detalleOrden);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}