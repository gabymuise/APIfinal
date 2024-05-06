package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.mappers.PlatoMapper;
import com.example.restaurant.models.EmpleadoModel;
import com.example.restaurant.models.PlatoModel;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private PlatoMapper platoMapper;

    @Transactional
    public PlatoResponse crearPlato(PlatoRequest platoRequest, EmpleadoModel chef) {
        PlatoModel plato = platoMapper.mapToPlatoModel(platoRequest);
        plato.setIdChef(chef.getId()); // Asignar el ID del chef al plato
        PlatoModel platoGuardado = platoRepository.save(plato);
        return platoMapper.mapToPlatoResponse(platoGuardado);
    }

    @Transactional(readOnly = true)
    public List<PlatoResponse> listarPlatos() {
        List<PlatoModel> platos = platoRepository.findAll();
        return platos.stream()
                .map(platoMapper::mapToPlatoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarPlato(Long id) {
        platoRepository.deleteById(id);
    }

    public PlatoResponse obtenerPlatoPorId(Long id) {
        PlatoModel plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        return platoMapper.mapToPlatoResponse(plato);
    }
}

