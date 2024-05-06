package com.example.restaurant.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "platos")
@Component
public class PlatoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "id_chef")
    private Long idChef;

    // Puedes agregar más campos según las necesidades de tu aplicación

}

