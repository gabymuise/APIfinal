package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.models.PlatoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatoMapper {
    @Autowired
    private EmpleadoMapper empleadoMapper;

    public PlatoModel mapToPlatoModel(PlatoRequest platoRequest) {
        PlatoModel plato = new PlatoModel();
        plato.setNombre(platoRequest.getNombre());
        plato.setDescripcion(platoRequest.getDescripcion());
        plato.setPrecio(platoRequest.getPrecio());
        plato.setIdChef(platoRequest.getIdChef());
        // Puedes mapear más campos según las necesidades de tu aplicación
        return plato;
    }

    public PlatoResponse mapToPlatoResponse(PlatoModel platoModel) {
        PlatoResponse platoResponse = new PlatoResponse();
        platoResponse.setId(platoModel.getId());
        platoResponse.setNombre(platoModel.getNombre());
        platoResponse.setDescripcion(platoModel.getDescripcion());
        platoResponse.setPrecio(platoModel.getPrecio());
        platoResponse.setIdChef(platoModel.getIdChef());
        // Puedes mapear más campos según las necesidades de tu aplicación
        return platoResponse;
    }
}
