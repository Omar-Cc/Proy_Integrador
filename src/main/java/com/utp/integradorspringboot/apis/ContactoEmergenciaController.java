package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.ContactoEmergencia;
import com.utp.integradorspringboot.repositories.ContactoEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacto-emergencias")
public class ContactoEmergenciaController {

    @Autowired
    private ContactoEmergenciaRepository contactoEmergenciaRepository;

    @GetMapping
    public List<ContactoEmergencia> getAllContactoEmergencias() {
        return contactoEmergenciaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoEmergencia> getContactoEmergenciaById(@PathVariable Integer id) {
        return contactoEmergenciaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ContactoEmergencia createContactoEmergencia(@RequestBody ContactoEmergencia contactoEmergencia) {
        return contactoEmergenciaRepository.save(contactoEmergencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactoEmergencia> updateContactoEmergencia(@PathVariable Integer id, @RequestBody ContactoEmergencia contactoEmergenciaDetails) {
        return contactoEmergenciaRepository.findById(id)
                .map(contactoEmergencia -> {
                    contactoEmergencia.setPaciente(contactoEmergenciaDetails.getPaciente());
                    contactoEmergencia.setNombre(contactoEmergenciaDetails.getNombre());
                    contactoEmergencia.setRelacion(contactoEmergenciaDetails.getRelacion());
                    contactoEmergencia.setTelefono(contactoEmergenciaDetails.getTelefono());
                    contactoEmergencia.setEmail(contactoEmergenciaDetails.getEmail());
                    contactoEmergencia.setDireccion(contactoEmergenciaDetails.getDireccion());
                    contactoEmergencia.setFechaRegistro(contactoEmergenciaDetails.getFechaRegistro());
                    return ResponseEntity.ok(contactoEmergenciaRepository.save(contactoEmergencia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactoEmergencia(@PathVariable Integer id) {
        return contactoEmergenciaRepository.findById(id)
                .map(contactoEmergencia -> {
                    contactoEmergenciaRepository.delete(contactoEmergencia);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}