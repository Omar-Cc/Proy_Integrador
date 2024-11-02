package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Reporte;
import com.utp.integradorspringboot.repositories.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    @GetMapping
    public List<Reporte> getAllReportes() {
        return reporteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> getReporteById(@PathVariable Integer id) {
        return reporteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reporte createReporte(@RequestBody Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> updateReporte(@PathVariable Integer id, @RequestBody Reporte reporte) {
        return reporteRepository.findById(id)
                .map(existingReporte -> {
                    reporte.setId(existingReporte.getId());
                    return ResponseEntity.ok(reporteRepository.save(reporte));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Integer id) {
        return reporteRepository.findById(id)
                .map(reporte -> {
                    reporteRepository.delete(reporte);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}