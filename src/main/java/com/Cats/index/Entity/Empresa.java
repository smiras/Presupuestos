package com.Cats.index.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String domicilio;
    private String telefono;
    private String email;
    private String cuit;

    @OneToMany
    private List<Aplication> aplications;

    @OneToMany(mappedBy = "empresa")
    private List<User> usuarios;
}
