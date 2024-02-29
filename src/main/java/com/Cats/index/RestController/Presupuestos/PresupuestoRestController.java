package com.Cats.index.RestController.Presupuestos;

import com.Cats.index.Entity.Presupuestos.Presupuesto;
import com.Cats.index.Request.Presupuestos.PresupuestoRequest;
import com.Cats.index.Service.Presupuestos.PresupuestoService;
import com.Cats.index.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/presupuestos")
public class PresupuestoRestController {

    @Autowired
    private PresupuestoService presupuestoService;

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public List<Presupuesto> listar(){
        return presupuestoService.listarPresupuestosByUser(userService.obtenerUsuarioAutenticado());
    }

    @PostMapping("/save")
    public ResponseEntity<Presupuesto> save(@RequestBody PresupuestoRequest presupuestoRequest){

        Presupuesto presupuesto = Presupuesto.builder()
                .importeTotal(presupuestoRequest.getImporteTotal())
                .dominio(presupuestoRequest.getDominio())
                .empresa(presupuestoRequest.getEmpresa())
                .descuentoAdministrativo(presupuestoRequest.getDescuentoAdministrativo())
                .comision(presupuestoRequest.getComision())
                .totalGeneral(presupuestoRequest.getTotalGeneral())
                .fechaFacturado(presupuestoRequest.getFechaFacturado())
                .fechaPagado(presupuestoRequest.getFechaPagado())
                .mesIngreso(presupuestoRequest.getMesIngreso())
                .km(presupuestoRequest.getKm())
                .estado(presupuestoRequest.getEstado())
                .motor(presupuestoRequest.getMotor())
                .telefono(presupuestoRequest.getTelefono())
                .vehiculo(presupuestoRequest.getVehiculo())
                .fecha(presupuestoRequest.getFecha())
                .detalle(presupuestoRequest.getDetalle())
                .comentario(presupuestoRequest.getComentario())
                .user(userService.obtenerUsuarioAutenticado())
                .build();

        return ResponseEntity.ok(presupuestoService.save(presupuesto));
    }

    @PostMapping("/empty")
    public Presupuesto empty(){
        return new Presupuesto();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        String delete = presupuestoService.delete(id);
        return new ResponseEntity<>(delete, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/recovery/{id}")
    public ResponseEntity<Presupuesto> recovery(@PathVariable Integer id){
        return ResponseEntity.ok(presupuestoService.recovery(id));
    }

    @PostMapping("/edit")
    public ResponseEntity<Presupuesto> edit(@RequestBody Presupuesto presupuesto){
        presupuesto.setUser(userService.obtenerUsuarioAutenticado());
        return ResponseEntity.ok(presupuestoService.save(presupuesto));
    }
}
