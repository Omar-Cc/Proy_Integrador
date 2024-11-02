package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Especialidad;
import com.utp.integradorspringboot.repositories.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping
    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidad> getEspecialidadById(@PathVariable Integer id) {
        return especialidadRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Especialidad createEspecialidad(@RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidad> updateEspecialidad(@PathVariable Integer id, @RequestBody Especialidad especialidad) {
        return especialidadRepository.findById(id)
                .map(existingEspecialidad -> {
                    especialidad.setId(existingEspecialidad.getId());
                    return ResponseEntity.ok(especialidadRepository.save(especialidad));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Integer id) {
        return especialidadRepository.findById(id)
                .map(especialidad -> {
                    especialidadRepository.delete(especialidad);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}