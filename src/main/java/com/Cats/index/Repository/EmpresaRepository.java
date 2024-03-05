package com.Cats.index.Repository;

import com.Cats.index.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Boolean existsByEmail(String email);

    Empresa findByNombre(String nombre);

    Empresa findById(Long empresaId);
}
