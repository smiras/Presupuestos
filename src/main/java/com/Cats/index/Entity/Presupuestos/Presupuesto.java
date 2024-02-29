package com.Cats.index.Entity.Presupuestos;

import com.Cats.index.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float importeTotal;
    private String dominio;
    private String empresa;
    private String descuentoAdministrativo;
    private Float comision;
    private Float totalGeneral;
    private String fechaFacturado;
    private String fechaPagado;
    private String mesIngreso;
    private String km;
    private String estado;
    private String motor;
    private String vehiculo;
    private String fecha;
    private String telefono;
    private String comentario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "presupuesto_id")
    private List<DetallePresupuesto> detalle;

    @ManyToOne
    private User user;

}
