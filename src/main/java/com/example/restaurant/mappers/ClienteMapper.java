package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.ClienteRequest;
import com.example.restaurant.dtos.responses.ClienteResponse;
import com.example.restaurant.models.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public Cliente toCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setEmail(clienteRequest.getEmail());
        return cliente;
    }

    public ClienteResponse toClienteResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setId(cliente.getId());
        clienteResponse.setNombre(cliente.getNombre());
        clienteResponse.setEmail(cliente.getEmail());
        return clienteResponse;
    }

    public void updateClienteFromRequest(ClienteRequest clienteRequest, Cliente cliente) {
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setEmail(clienteRequest.getEmail());
    }
}

