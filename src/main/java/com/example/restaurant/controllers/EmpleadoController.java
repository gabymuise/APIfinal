package com.example.restaurant.controllers;

import com.example.restaurant.dtos.requests.EmpleadoRequest;
import com.example.restaurant.dtos.responses.EmpleadoResponse;
import com.example.restaurant.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoResponse> crearEmpleado(@RequestBody EmpleadoRequest empleadoRequest) {
        EmpleadoResponse nuevoEmpleado = empleadoService.crearEmpleado(empleadoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpleado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EmpleadoResponse>> listarEmpleados() {
        List<EmpleadoResponse> empleados = empleadoService.listarEmpleados();
        return ResponseEntity.ok(empleados);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrarEmpleado(@PathVariable("id") Long id) {
        empleadoService.eliminarEmpleado(id);
        return ResponseEntity.ok("Empleado eliminado correctamente");
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> obtenerEmpleadoPorId(@PathVariable Long id) {
        EmpleadoResponse empleado = empleadoService.obtenerEmpleadoPorId(id);
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> actualizarEmpleado(@PathVariable Long id, @RequestBody EmpleadoRequest empleadoRequest) {
        EmpleadoResponse empleadoActualizado = empleadoService.actualizarEmpleado(id, empleadoRequest);
        return ResponseEntity.ok(empleadoActualizado);
    }
}


