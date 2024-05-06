package com.example.restaurant.repositories;

import com.example.restaurant.models.PlatoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<PlatoModel, Long> {
}
