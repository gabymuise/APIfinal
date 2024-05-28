package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.exceptions.ResourceNotFoundException;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlatoMapper {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Plato toPlatoModel(PlatoRequest platoRequest) {
        Plato plato = new Plato();
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
        plato.setCategoria(categoriaRepository.findById(platoRequest.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + platoRequest.getCategoriaId())));
        return plato;
    }

    public PlatoResponse toPlatoResponse(Plato plato) {
        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setId(plato.getId());
        platoResponse.setNombre(plato.getNombre());
        platoResponse.setDescripcion(plato.getDescripcion());
        platoResponse.setPrecio(plato.getPrecio());
        platoResponse.setCategoriaId(plato.getCategoria().getId());
        return platoResponse;
    }
}
