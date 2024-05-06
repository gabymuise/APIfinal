package com.example.restaurant.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoResponse {
    private Long id;
    private String nombre;
    private String cargo;
    // Puedes agregar más campos según las necesidades de tu aplicación
}

