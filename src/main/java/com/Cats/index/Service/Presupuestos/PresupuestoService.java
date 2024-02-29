package com.Cats.index.Service.Presupuestos;

import com.Cats.index.Entity.Presupuestos.Presupuesto;
import com.Cats.index.Entity.User;
import com.Cats.index.Repository.Presupuestos.PresupuestoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PresupuestoService {

    @Autowired
    private PresupuestoRepository presupuestoRepository;

    public List<Presupuesto> listarPresupuestosByUser(User user){
        return presupuestoRepository.findAllByUser(user);
    }

    public Presupuesto save(Presupuesto presupuesto){
        return presupuestoRepository.save(presupuesto);
    }

    public String delete(Integer id) {
        Optional<Presupuesto> presupuestoOptional = presupuestoRepository.findById(id);
        if (presupuestoOptional.isPresent()) {
            presupuestoRepository.deleteById(id);
            return "Presupuesto con ID " + id + " eliminado con éxito";
        } else {
            throw new NoSuchElementException("No se encontró un presupuesto con el ID " + id);
        }
    }

    public Presupuesto recovery(Integer id){
        Optional<Presupuesto> presupuesto = presupuestoRepository.findById(id);
        return presupuesto.get();
    }

    public int verAceiteGuardado(Long id){
        return presupuestoRepository.findCantAceiteById(id);
    }
}