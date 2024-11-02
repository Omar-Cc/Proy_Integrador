package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.ResultadoLaboratorio;
import com.utp.integradorspringboot.repositories.ResultadoLaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados-laboratorio")
public class ResultadoLaboratorioController {

    @Autowired
    private ResultadoLaboratorioRepository resultadoLaboratorioRepository;

    @GetMapping
    public List<ResultadoLaboratorio> getAllResultadosLaboratorio() {
        return resultadoLaboratorioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoLaboratorio> getResultadoLaboratorioById(@PathVariable Integer id) {
        return resultadoLaboratorioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResultadoLaboratorio createResultadoLaboratorio(@RequestBody ResultadoLaboratorio resultadoLaboratorio) {
        return resultadoLaboratorioRepository.save(resultadoLaboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultadoLaboratorio> updateResultadoLaboratorio(@PathVariable Integer id, @RequestBody ResultadoLaboratorio resultadoLaboratorio) {
        return resultadoLaboratorioRepository.findById(id)
                .map(existingResultadoLaboratorio -> {
                    resultadoLaboratorio.setId(existingResultadoLaboratorio.getId());
                    return ResponseEntity.ok(resultadoLaboratorioRepository.save(resultadoLaboratorio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultadoLaboratorio(@PathVariable Integer id) {
        return resultadoLaboratorioRepository.findById(id)
                .map(resultadoLaboratorio -> {
                    resultadoLaboratorioRepository.delete(resultadoLaboratorio);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}