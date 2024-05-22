package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.mappers.EmpleadoMapper;
import com.example.restaurant.models.Empleado;
import com.example.restaurant.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    public EmpleadoResponse crearEmpleado(EmpleadoRequest empleadoRequest) {
        Empleado empleado = empleadoMapper.mapToEmpleadoModel(empleadoRequest);
        Empleado nuevoEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.mapToEmpleadoResponse(nuevoEmpleado);
    }

    public List<EmpleadoResponse> listarEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados.stream()
                .map(empleadoMapper::mapToEmpleadoResponse)
                .collect(Collectors.toList());
    }

    public void eliminarEmpleado(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El empleado con el ID proporcionado no existe");
        }
    }

    public EmpleadoResponse obtenerEmpleadoPorId(Long idEmpleado) {
        Empleado empleado = empleadoRepository.findById(idEmpleado)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con el ID proporcionado no existe"));
        return empleadoMapper.mapToEmpleadoResponse(empleado);
    }

    public EmpleadoResponse actualizarEmpleado(Long id, EmpleadoRequest empleadoRequest) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con el ID proporcionado no existe"));
        empleadoMapper.updateEmpleadoFromRequest(empleadoRequest, empleado);
        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.mapToEmpleadoResponse(empleado);
    }
}


