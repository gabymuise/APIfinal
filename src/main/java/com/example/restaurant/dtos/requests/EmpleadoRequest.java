package com.example.restaurant.dtos.requests;

import com.example.restaurant.models.Cliente;
import com.example.restaurant.models.Plato;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpleadoRequest {
    private String nombre;
    private String cargo;
    private Cliente cliente;
    private Plato plato;

}

