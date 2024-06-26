package com.example.restaurant.services;

import com.example.restaurant.dtos.responses.ClienteResponse;
import com.example.restaurant.dtos.requests.ClienteRequest;
import com.example.restaurant.mappers.ClienteMapper;
import com.example.restaurant.models.Cliente;
import com.example.restaurant.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    @Transactional
    public ClienteResponse crearCliente(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toClienteModel(clienteRequest);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteResponse(cliente);
    }

    @Transactional
    public ClienteResponse obtenerClientePorId(Long clienteId) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            return clienteMapper.toClienteResponse(cliente);
        } else {
            throw new IllegalArgumentException("El cliente con el ID proporcionado no existe");
        }
    }

    @Transactional
    public List<ClienteResponse> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(clienteMapper::toClienteResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClienteResponse actualizarCliente(Long clienteId, ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("El cliente con el ID proporcionado no existe"));
        clienteMapper.updateClienteFromRequest(clienteRequest, cliente);
        cliente = clienteRepository.save(cliente);
        return clienteMapper.toClienteResponse(cliente);
    }

    @Transactional
    public void eliminarClientePorId(Long clienteId) {
        if (clienteRepository.existsById(clienteId)) {
            clienteRepository.deleteById(clienteId);
        } else {
            throw new IllegalArgumentException("El cliente con el ID proporcionado no existe");
        }
    }
}


