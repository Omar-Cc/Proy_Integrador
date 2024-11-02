package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.Costo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostoEspecialidadRepository extends JpaRepository<Costo, Integer> {
}