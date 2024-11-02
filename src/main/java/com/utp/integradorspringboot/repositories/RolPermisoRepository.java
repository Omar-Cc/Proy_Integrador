package com.utp.integradorspringboot.repositories;

import com.utp.integradorspringboot.models.RolPermiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolPermisoRepository extends JpaRepository<RolPermiso, Integer> {
}