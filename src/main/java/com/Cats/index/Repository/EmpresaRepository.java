package com.Cats.index.Repository;

import com.Cats.index.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    Boolean existsByEmail(String email);

    Empresa findByNombre(String nombre);

    Empresa findById(Long empresaId);

    @Query("SELECT e FROM Empresa e JOIN e.usuarios u WHERE u.id = :userId")
    Empresa findByUserId(@Param("userId") Long userId);
}
