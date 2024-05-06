package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.models.EmpleadoModel;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {
    public EmpleadoModel mapToEmpleadoModel(EmpleadoRequest empleadoRequest) {
        EmpleadoModel empleado = new EmpleadoModel();
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setCargo(empleadoRequest.getCargo());
        // Puedes mapear más campos según las necesidades de tu aplicación
        return empleado;
    }

    public EmpleadoResponse mapToEmpleadoResponse(EmpleadoModel empleadoModel) {
        EmpleadoResponse empleadoResponse = new EmpleadoResponse();
        empleadoResponse.setId(empleadoModel.getId());
        empleadoResponse.setNombre(empleadoModel.getNombre());
        empleadoResponse.setCargo(empleadoModel.getCargo());
        // Puedes mapear más campos según las necesidades de tu aplicación
        return empleadoResponse;
    }
}


