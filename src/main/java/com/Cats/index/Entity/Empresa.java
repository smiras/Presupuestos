package com.Cats.index.Entity;

import com.Cats.index.Enum.Services;
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

    private List<Services> services;

    @OneToMany(mappedBy = "empresa")
    private List<User> usuarios;
}
