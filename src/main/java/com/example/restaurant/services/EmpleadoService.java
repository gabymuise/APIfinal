package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.mappers.EmpleadoMapper;
import com.example.restaurant.models.Cliente;
import com.example.restaurant.models.Empleado;
import com.example.restaurant.models.Plato;
import com.example.restaurant.repositories.ClienteRepository;
import com.example.restaurant.repositories.EmpleadoRepository;
import com.example.restaurant.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {

    @Autowired
    private PlatoRepository platoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private final EmpleadoRepository empleadoRepository;
    @Autowired
    private final EmpleadoMapper empleadoMapper;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository, EmpleadoMapper empleadoMapper) {
        this.empleadoRepository = empleadoRepository;
        this.empleadoMapper = empleadoMapper;
    }

    @Transactional
    public EmpleadoResponse crearEmpleado(EmpleadoRequest empleadoRequest) {
        Empleado empleado = empleadoMapper.toEmpleadoModel(empleadoRequest);

        if (empleadoRequest.getPlatosIds() != null && !empleadoRequest.getPlatosIds().isEmpty()) {
            Set<Plato> platos = empleadoRequest.getPlatosIds().stream()
                    .map(platoId -> platoRepository.findById(platoId)
                            .orElseThrow(() -> new IllegalArgumentException("El plato con el ID proporcionado no existe")))
                    .collect(Collectors.toSet());
            empleado.setPlatos(platos);
        }

        if (empleadoRequest.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(empleadoRequest.getClienteId())
                    .orElseThrow(() -> new IllegalArgumentException("El cliente con el ID proporcionado no existe"));
            empleado.setCliente(cliente);
        }

        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.toEmpleadoResponse(empleado);
    }


    @Transactional
    public EmpleadoResponse obtenerEmpleadoPorId(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con el ID proporcionado no existe"));
        return empleadoMapper.toEmpleadoResponse(empleado);
    }

    @Transactional
    public List<EmpleadoResponse> listarEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();
        return empleados.stream()
                .map(empleadoMapper::toEmpleadoResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EmpleadoResponse actualizarEmpleado(Long id, EmpleadoRequest empleadoRequest) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El empleado con el ID proporcionado no existe"));
        empleadoMapper.updateEmpleadoFromRequest(empleadoRequest, empleado);
        empleado = empleadoRepository.save(empleado);
        return empleadoMapper.toEmpleadoResponse(empleado);
    }

    @Transactional
    public void eliminarEmpleado(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("El empleado con el ID proporcionado no existe");
        }
    }
}
