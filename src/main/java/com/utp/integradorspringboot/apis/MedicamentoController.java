package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Medicamento;
import com.utp.integradorspringboot.repositories.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @GetMapping
    public List<Medicamento> getAllMedicamentos() {
        return medicamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicamento> getMedicamentoById(@PathVariable Integer id) {
        return medicamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Medicamento createMedicamento(@RequestBody Medicamento medicamento) {
        return medicamentoRepository.save(medicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Integer id, @RequestBody Medicamento medicamento) {
        return medicamentoRepository.findById(id)
                .map(existingMedicamento -> {
                    medicamento.setId(existingMedicamento.getId());
                    return ResponseEntity.ok(medicamentoRepository.save(medicamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamento(@PathVariable Integer id) {
        return medicamentoRepository.findById(id)
                .map(medicamento -> {
                    medicamentoRepository.delete(medicamento);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}