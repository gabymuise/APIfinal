package com.example.restaurant.controllers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.models.EmpleadoModel;
import com.example.restaurant.services.EmpleadoService;
import com.example.restaurant.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired(required = true)
    private final PlatoService platoService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired(required = true)
    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PlatoResponse> crearPlato(@RequestBody PlatoRequest platoRequest) {
        Optional<EmpleadoModel> chefOptional = Optional.ofNullable(empleadoService.obtenerEmpleadoPorId(platoRequest.getIdChef()));
        if (chefOptional.isPresent()) {
            PlatoResponse nuevoPlato = platoService.crearPlato(platoRequest, chefOptional.get());
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPlato);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PlatoResponse>> listarPlatos() {
        List<PlatoResponse> platos = platoService.listarPlatos();
        return ResponseEntity.ok(platos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatoResponse> obtenerPlatoPorId(@PathVariable Long id) {
        PlatoResponse plato = platoService.obtenerPlatoPorId(id);
        return ResponseEntity.ok(plato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPlato(@PathVariable Long id) {
        platoService.eliminarPlato(id);
        return ResponseEntity.ok("Plato eliminado");
    }
}

