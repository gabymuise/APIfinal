package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.mappers.PlatoMapper;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatoService {

    private final PlatoRepository platoRepository;
    private final PlatoMapper platoMapper;

    @Autowired
    public PlatoService(PlatoRepository platoRepository, PlatoMapper platoMapper) {
        this.platoRepository = platoRepository;
        this.platoMapper = platoMapper;
    }

    @Transactional
    public PlatoResponse crearPlato(PlatoRequest platoRequest) {
        Plato plato = platoMapper.mapToPlatoModel(platoRequest);
        Plato platoGuardado = platoRepository.save(plato);
        return platoMapper.mapToPlatoResponse(platoGuardado);
    }

    @Transactional(readOnly = true)
    public List<PlatoResponse> listarPlatos() {
        List<Plato> platos = platoRepository.findAll();
        return platos.stream()
                .map(platoMapper::mapToPlatoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarPlato(Long id) {
        if (!platoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Plato no encontrado con id: " + id);
        }
        platoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public PlatoResponse obtenerPlatoPorId(Long id) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        return platoMapper.mapToPlatoResponse(plato);
    }

    @Transactional
    public PlatoResponse actualizarPlato(Long id, PlatoRequest platoRequest) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        platoMapper.updatePlatoFromRequest(platoRequest, plato);
        Plato platoActualizado = platoRepository.save(plato);
        return platoMapper.mapToPlatoResponse(platoActualizado);
    }
}

