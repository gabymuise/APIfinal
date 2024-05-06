package com.example.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restaurant.models.EmpleadoModel;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Long> {
}
