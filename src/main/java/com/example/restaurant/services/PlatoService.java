package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.exceptions.ResourceNotFoundException;
import com.example.restaurant.mappers.PlatoMapper;
import com.example.restaurant.models.Categoria;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.CategoriaRepository;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlatoService {

    @Autowired
    private PlatoRepository platoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PlatoMapper platoMapper;
    @Transactional
    public PlatoResponse crearPlato(PlatoRequest platoRequest) {
        Plato plato = platoMapper.toPlatoModel(platoRequest);

        if (platoRequest.getCategoriaIds() != null && !platoRequest.getCategoriaIds().isEmpty()) {
            Categoria categoria = categoriaRepository.findById(platoRequest.getCategoriaIds().iterator().next())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + platoRequest.getCategoriaIds().iterator().next()));
            plato.setCategoria(categoria);
        }

        Plato savedPlato = platoRepository.save(plato);
        return platoMapper.toPlatoResponse(savedPlato);
    }


    @Transactional
    public PlatoResponse actualizarPlato(Long id, PlatoRequest platoRequest) {
        Plato existingPlato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));

        existingPlato.setNombre(platoRequest.getNombre());
        existingPlato.setDescripcion(platoRequest.getDescripcion());
        existingPlato.setPrecio(platoRequest.getPrecio());

        Plato updatedPlato = platoRepository.save(existingPlato);
        return platoMapper.toPlatoResponse(updatedPlato);
    }
    @Transactional
    public List<PlatoResponse> listarPlatos() {
        List<Plato> platos = platoRepository.findAll();
        return platos.stream()
                .map(platoMapper::toPlatoResponse)
                .collect(Collectors.toList());
    }
    @Transactional
    public PlatoResponse obtenerPlatoPorId(Long id) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        return platoMapper.toPlatoResponse(plato);
    }
    @Transactional
    public void eliminarPlato(Long id) {
        Plato plato = platoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plato no encontrado con id: " + id));
        platoRepository.delete(plato);
    }
}
