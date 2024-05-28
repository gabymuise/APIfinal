package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.exceptions.ResourceNotFoundException;
import com.example.restaurant.mappers.PlatoMapper;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.CategoriaRepository;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlatoService {

    private final PlatoRepository platoRepository;
    private final PlatoMapper platoMapper;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public PlatoService(PlatoRepository platoRepository, PlatoMapper platoMapper, CategoriaRepository categoriaRepository) {
        this.platoRepository = platoRepository;
        this.platoMapper = platoMapper;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public PlatoResponse crearPlato(PlatoRequest platoRequest) {
        Plato plato = platoMapper.toPlatoModel(platoRequest);
        Plato nuevoPlato = platoRepository.save(plato);
        return platoMapper.toPlatoResponse(nuevoPlato);
    }

    @Transactional(readOnly = true)
    public List<PlatoResponse> listarPlatos() {
        List<Plato> platos = platoRepository.findAll();
        return platos.stream()
                .map(platoMapper::toPlatoResponse)
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
        return platoMapper.toPlatoResponse(plato);
    }

    @Transactional
    public PlatoResponse actualizarPlato(Long id, PlatoRequest platoRequest) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
        plato.setCategoria(categoriaRepository.findById(platoRequest.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + platoRequest.getCategoriaId())));
        Plato platoActualizado = platoRepository.save(plato);
        return platoMapper.toPlatoResponse(platoActualizado);
    }
}
