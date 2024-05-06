package com.example.restaurant.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long idChef;
    // Puedes agregar más campos según las necesidades de tu aplicación
}

