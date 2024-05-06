package com.example.restaurant.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatoRequest {
    private String nombre;
    private String descripcion;
    private Double precio;
    private Long idChef;
    // Puedes agregar más campos según las necesidades de tu aplicación
}
