package com.example.restaurant.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PlatoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Set<Long> categoriaIds;
    private Set<String> categoriaNombres;
}
