package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.mappers.EmpleadoMapper;
import com.example.restaurant.models.EmpleadoModel;
import com.example.restaurant.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public ResponseEntity<EmpleadoResponse> crearEmpleado(EmpleadoRequest empleadoRequest) {
        EmpleadoModel empleado = empleadoMapper.mapToEmpleadoModel(empleadoRequest);
        EmpleadoModel nuevoEmpleado = empleadoRepository.save(empleado);
        EmpleadoResponse empleadoResponse = empleadoMapper.mapToEmpleadoResponse(nuevoEmpleado);
        return ResponseEntity.ok(empleadoResponse);
    }

    public List<EmpleadoResponse> listarEmpleados() {
        List<EmpleadoModel> empleados = empleadoRepository.findAll();
        return empleados.stream()
                .map(empleadoMapper::mapToEmpleadoResponse)
                .collect(Collectors.toList());
    }

    public ResponseEntity<String> eliminarEmpleado(Long id) {
        Optional<EmpleadoModel> empleadoOptional = empleadoRepository.findById(id);
        if (empleadoOptional.isPresent()) {
            empleadoRepository.deleteById(id);
            return ResponseEntity.ok("Empleado eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public EmpleadoModel obtenerEmpleadoPorId(Long idEmpleado) {
        Optional<EmpleadoModel> empleadoOptional = empleadoRepository.findById(idEmpleado);
        if (empleadoOptional.isPresent()) {
            return empleadoOptional.get();
        } else {
            throw new IllegalArgumentException("El empleado con el ID proporcionado no existe");
        }
    }
}

