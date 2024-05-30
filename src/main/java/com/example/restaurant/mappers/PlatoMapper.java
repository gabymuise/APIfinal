package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class PlatoMapper {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Plato toPlatoModel(PlatoRequest platoRequest) {
        Plato plato = new Plato();
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
        return plato;
    }

    public PlatoResponse toPlatoResponse(Plato plato) {
        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setId(plato.getId());
        platoResponse.setNombre(plato.getNombre());
        platoResponse.setDescripcion(plato.getDescripcion());
        platoResponse.setPrecio(plato.getPrecio());

        if (plato.getCategoria() != null) {
            platoResponse.setCategoriaIds(Collections.singleton(plato.getCategoria().getId()));
            platoResponse.setCategoriaNombres(Collections.singleton(plato.getCategoria().getNombre()));
        }

        return platoResponse;
    }
}
