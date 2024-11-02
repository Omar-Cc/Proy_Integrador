package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Turno;
import com.utp.integradorspringboot.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoRepository turnoRepository;

    @GetMapping
    public List<Turno> getAllTurnos() {
        return turnoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable Integer id) {
        return turnoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turno createTurno(@RequestBody Turno turno) {
        return turnoRepository.save(turno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable Integer id, @RequestBody Turno turno) {
        return turnoRepository.findById(id)
                .map(existingTurno -> {
                    turno.setId(existingTurno.getId());
                    return ResponseEntity.ok(turnoRepository.save(turno));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTurno(@PathVariable Integer id) {
        return turnoRepository.findById(id)
                .map(turno -> {
                    turnoRepository.delete(turno);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}