package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.models.Categoria;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatoMapper {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Plato mapToPlatoModel(PlatoRequest platoRequest) {
        Plato plato = new Plato();
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());

        Categoria categoria = categoriaRepository.findById(platoRequest.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        plato.setCategoria(categoria);

        return plato;
    }

    public PlatoResponse mapToPlatoResponse(Plato plato) {
        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setId(plato.getId());
        platoResponse.setNombre(plato.getNombre());
        platoResponse.setDescripcion(plato.getDescripcion());
        platoResponse.setPrecio(plato.getPrecio());
        platoResponse.setCategoriaId(plato.getCategoria().getId());
        return platoResponse;
    }

    public void updatePlatoFromRequest(PlatoRequest platoRequest, Plato plato) {
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());

        Categoria categoria = categoriaRepository.findById(platoRequest.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
        plato.setCategoria(categoria);
    }
}

