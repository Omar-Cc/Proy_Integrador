package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Auditoria;
import com.utp.integradorspringboot.repositories.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditorias")
public class AuditoriaController {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    @GetMapping
    public List<Auditoria> getAllAuditorias() {
        return auditoriaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auditoria> getAuditoriaById(@PathVariable Integer id) {
        return auditoriaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Auditoria createAuditoria(@RequestBody Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auditoria> updateAuditoria(@PathVariable Integer id, @RequestBody Auditoria auditoriaDetails) {
        return auditoriaRepository.findById(id)
                .map(auditoria -> {
                    auditoria.setUsuario(auditoriaDetails.getUsuario());
                    auditoria.setAccion(auditoriaDetails.getAccion());
                    auditoria.setTablaAfectada(auditoriaDetails.getTablaAfectada());
                    auditoria.setIdRegistroAfectado(auditoriaDetails.getIdRegistroAfectado());
                    auditoria.setFecha(auditoriaDetails.getFecha());
                    auditoria.setHora(auditoriaDetails.getHora());
                    auditoria.setDescripcion(auditoriaDetails.getDescripcion());
                    return ResponseEntity.ok(auditoriaRepository.save(auditoria));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditoria(@PathVariable Integer id) {
        return auditoriaRepository.findById(id)
                .map(auditoria -> {
                    auditoriaRepository.delete(auditoria);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}