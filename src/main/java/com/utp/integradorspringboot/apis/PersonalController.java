package com.utp.integradorspringboot.apis;

import com.utp.integradorspringboot.models.Personal;
import com.utp.integradorspringboot.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personales")
public class PersonalController {

    @Autowired
    private PersonalRepository personalRepository;

    @GetMapping
    public List<Personal> getAllPersonales() {
        return personalRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> getPersonalById(@PathVariable Integer id) {
        return personalRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Personal createPersonal(@RequestBody Personal personal) {
        return personalRepository.save(personal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> updatePersonal(@PathVariable Integer id, @RequestBody Personal personal) {
        return personalRepository.findById(id)
                .map(existingPersonal -> {
                    personal.setId(existingPersonal.getId());
                    return ResponseEntity.ok(personalRepository.save(personal));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Integer id) {
        return personalRepository.findById(id)
                .map(personal -> {
                    personalRepository.delete(personal);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}