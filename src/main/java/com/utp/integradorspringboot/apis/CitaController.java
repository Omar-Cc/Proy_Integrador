package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Cita;
import com.utp.integradorspringboot.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaRepository citaRepository;

    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Integer id) {
        return citaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cita createCita(@RequestBody Cita cita) {
        return citaRepository.save(cita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Integer id, @RequestBody Cita citaDetails) {
        return citaRepository.findById(id)
                .map(cita -> {
                    cita.setPaciente(citaDetails.getPaciente());
                    cita.setMedico(citaDetails.getMedico());
                    cita.setFechaCita(citaDetails.getFechaCita());
                    cita.setHoraCita(citaDetails.getHoraCita());
                    cita.setMotivo(citaDetails.getMotivo());
                    cita.setEstado(citaDetails.getEstado());
                    cita.setFechaRegistrada(citaDetails.getFechaRegistrada());
                    cita.setHoraRegistrada(citaDetails.getHoraRegistrada());
                    cita.setHistorialClinico(citaDetails.getHistorialClinico());
                    return ResponseEntity.ok(citaRepository.save(cita));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Integer id) {
        return citaRepository.findById(id)
                .map(cita -> {
                    citaRepository.delete(cita);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}