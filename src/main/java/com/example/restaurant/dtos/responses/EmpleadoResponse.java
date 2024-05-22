package com.example.restaurant.dtos.responses;

import com.example.restaurant.models.Cliente;
import com.example.restaurant.models.Plato;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoResponse {
    private Long id;
    private String nombre;
    private String cargo;
    private Cliente cliente;
    private Plato plato;
}

