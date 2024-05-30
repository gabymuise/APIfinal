package com.example.restaurant.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class EmpleadoRequest {
    private String nombre;
    private String cargo;
    private Long clienteId;
    private Set<Long> platosIds;

    public Set<Long> getPlatosIds() {
        return this.platosIds;
    }


}

