package com.Cats.index.Request.Presupuestos;

import com.Cats.index.Entity.Presupuestos.DetallePresupuesto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PresupuestoRequest {

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
    private int cantAceiteSintetico;
    private float precioAceiteSintetico;
    private int cantAceiteSemiSintetico;
    private float precioAceiteSemiSintetico;
    private String comentario;
    private List<DetallePresupuesto> detalle;

}
