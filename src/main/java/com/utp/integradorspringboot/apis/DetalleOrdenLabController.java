package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.DetalleOrdenLab;
import com.utp.integradorspringboot.repositories.DetalleOrdenLabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-orden-labs")
public class DetalleOrdenLabController {

    @Autowired
    private DetalleOrdenLabRepository detalleOrdenLabRepository;

    @GetMapping
    public List<DetalleOrdenLab> getAllDetalleOrdenLabs() {
        return detalleOrdenLabRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleOrdenLab> getDetalleOrdenLabById(@PathVariable Integer id) {
        return detalleOrdenLabRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleOrdenLab createDetalleOrdenLab(@RequestBody DetalleOrdenLab detalleOrdenLab) {
        return detalleOrdenLabRepository.save(detalleOrdenLab);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleOrdenLab> updateDetalleOrdenLab(@PathVariable Integer id, @RequestBody DetalleOrdenLab detalleOrdenLabDetails) {
        return detalleOrdenLabRepository.findById(id)
                .map(detalleOrdenLab -> {
                    detalleOrdenLab.setOrdenLaboratorio(detalleOrdenLabDetails.getOrdenLaboratorio());
                    detalleOrdenLab.setPruebaLaboratorio(detalleOrdenLabDetails.getPruebaLaboratorio());
                    detalleOrdenLab.setEstado(detalleOrdenLabDetails.getEstado());
                    detalleOrdenLab.setSubtotal(detalleOrdenLabDetails.getSubtotal());
                    detalleOrdenLab.setProgreso(detalleOrdenLabDetails.getProgreso());
                    return ResponseEntity.ok(detalleOrdenLabRepository.save(detalleOrdenLab));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetalleOrdenLab(@PathVariable Integer id) {
        return detalleOrdenLabRepository.findById(id)
                .map(detalleOrdenLab -> {
                    detalleOrdenLabRepository.delete(detalleOrdenLab);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}