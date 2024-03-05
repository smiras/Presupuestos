package com.Cats.index.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequest {

    private Long id;
    private String nombre;
    private String domicilio;
    private String telefono;
    private String email;
}
