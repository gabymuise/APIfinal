package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.models.Plato;
import org.springframework.stereotype.Service;

@Service
public class PlatoMapper {

    public Plato mapToPlatoModel(PlatoRequest platoRequest) {
        Plato plato = new Plato();
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
        return plato;
    }

    public PlatoResponse mapToPlatoResponse(Plato plato) {
        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setId(plato.getId());
        platoResponse.setNombre(plato.getNombre());
        platoResponse.setDescripcion(plato.getDescripcion());
        platoResponse.setPrecio(plato.getPrecio());
        return platoResponse;
    }

    public void updatePlatoFromRequest(PlatoRequest platoRequest, Plato plato) {
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
    }
}

