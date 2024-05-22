package com.example.restaurant.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.SecondaryTable.*;

@Entity
@Table(name = "empleados")
@Getter
@Setter
@Component
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cargo")
    private String cargo;

    @OneToOne
    @JoinTable(name = "Clientes",
            joinColumns = @JoinColumn(name = "id_Empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_cliente"))
    private Cliente cliente;

    @OneToOne
    @JoinTable(name = "Platos",
            joinColumns = @JoinColumn(name = "id_Empleado"),
            inverseJoinColumns = @JoinColumn(name = "id_Plato"))

    private Plato plato;

}

