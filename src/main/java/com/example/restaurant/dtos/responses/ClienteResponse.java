package com.example.restaurant.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
    private Long id;
    private String nombre;
    private String email;
}
