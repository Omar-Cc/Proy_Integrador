package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Integer> {
}