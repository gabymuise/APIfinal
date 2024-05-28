package com.example.restaurant.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long categoriaId;
}
