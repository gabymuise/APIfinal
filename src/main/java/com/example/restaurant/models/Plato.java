package com.example.restaurant.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "plato")
@Component
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // Getters and Setters
    // ...
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}

