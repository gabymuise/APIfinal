package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.models.Empleado;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.restaurant.models.Plato;



@Component
public class EmpleadoMapper {

    private final PlatoRepository platoRepository;

    @Autowired
    public EmpleadoMapper(PlatoRepository platoRepository) {
        this.platoRepository = platoRepository;
    }
    public Empleado toEmpleadoModel(EmpleadoRequest empleadoRequest) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setCargo(empleadoRequest.getCargo());

        if (empleadoRequest.getPlatosIds() != null && !empleadoRequest.getPlatosIds().isEmpty()) {
            Set<Plato> platos = empleadoRequest.getPlatosIds().stream()
                    .map(platoId -> platoRepository.findById(platoId)
                            .orElseThrow(() -> new IllegalArgumentException("El plato con el ID proporcionado no existe")))
                    .collect(Collectors.toSet());
            empleado.setPlatos(platos);
        }

        return empleado;
    }

    public EmpleadoResponse toEmpleadoResponse(Empleado empleado) {
        EmpleadoResponse empleadoResponse = new EmpleadoResponse();
        empleadoResponse.setId(empleado.getId());
        empleadoResponse.setNombre(empleado.getNombre());
        empleadoResponse.setCargo(empleado.getCargo());
        empleadoResponse.setCliente(empleado.getCliente());

        Set<Long> platoIds = empleado.getPlatos().stream()
                .map(Plato::getId)
                .collect(Collectors.toSet());
        empleadoResponse.setPlatoIds(platoIds);

        return empleadoResponse;
    }


    public void updateEmpleadoFromRequest(EmpleadoRequest empleadoRequest, Empleado empleado) {
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setCargo(empleadoRequest.getCargo());
    }
}
