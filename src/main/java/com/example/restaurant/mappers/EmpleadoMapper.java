package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.models.Empleado;
import org.springframework.stereotype.Component;

@Component
public class EmpleadoMapper {
    public Empleado mapToEmpleadoModel(EmpleadoRequest empleadoRequest) {
        Empleado empleado = new Empleado();
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setCargo(empleadoRequest.getCargo());
        empleado.setCliente(empleadoRequest.getCliente());
        empleado.setPlato(empleadoRequest.getPlato());

        return empleado;
    }

    public EmpleadoResponse mapToEmpleadoResponse(Empleado empleado) {
        EmpleadoResponse empleadoResponse = new EmpleadoResponse();
        empleadoResponse.setId(empleado.getId());
        empleadoResponse.setNombre(empleado.getNombre());
        empleadoResponse.setCargo(empleado.getCargo());
        empleadoResponse.setPlato(empleado.getPlato());
        empleadoResponse.setCliente(empleado.getCliente());

        return empleadoResponse;
    }

    public void updateEmpleadoFromRequest(EmpleadoRequest empleadoRequest, Empleado empleado) {
        empleado.setNombre(empleadoRequest.getNombre());
        empleado.setCargo(empleadoRequest.getCargo());
        empleado.setCliente(empleadoRequest.getCliente());
        empleado.setPlato(empleadoRequest.getPlato());
    }
}


