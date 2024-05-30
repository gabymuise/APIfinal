package com.example.restaurant.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PlatoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Set<Long> categoriaIds;
}
