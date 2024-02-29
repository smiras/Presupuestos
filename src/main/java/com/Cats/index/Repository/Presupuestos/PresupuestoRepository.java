package com.Cats.index.Repository.Presupuestos;

import com.Cats.index.Entity.Presupuestos.Presupuesto;
import com.Cats.index.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {

    int findCantAceiteById(Long id);

    List<Presupuesto> findAllByUser(User user);
}
