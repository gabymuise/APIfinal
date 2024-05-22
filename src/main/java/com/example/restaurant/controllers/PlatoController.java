package com.example.restaurant.controllers;

import com.example.restaurant.dtos.requests.PlatoRequest;
import com.example.restaurant.dtos.responses.PlatoResponse;
import com.example.restaurant.services.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    private final PlatoService platoService;

    @Autowired
    public PlatoController(PlatoService platoService) {
        this.platoService = platoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PlatoResponse> crearPlato(@RequestBody PlatoRequest platoRequest) {
        PlatoResponse nuevoPlato = platoService.crearPlato(platoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPlato);
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

    @PutMapping("/{id}")
    public ResponseEntity<PlatoResponse> actualizarPlato(@PathVariable Long id, @RequestBody PlatoRequest platoRequest) {
        PlatoResponse platoActualizado = platoService.actualizarPlato(id, platoRequest);
        return ResponseEntity.ok(platoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPlato(@PathVariable Long id) {
        platoService.eliminarPlato(id);
        return ResponseEntity.ok("Plato eliminado");
    }
}


