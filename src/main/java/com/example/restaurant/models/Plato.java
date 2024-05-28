package com.example.restaurant.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "platos")
@Getter
@Setter
@Component
public class Plato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinTable(name = "Plato_Categoria",
            joinColumns = @JoinColumn(name = "id_Plato"),
            inverseJoinColumns = @JoinColumn(name = "id_Categoria"))
    private Categoria categoria;
}
