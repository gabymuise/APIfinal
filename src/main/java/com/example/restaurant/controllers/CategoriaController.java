package com.example.restaurant.controllers;

import com.example.restaurant.dtos.requests.CategoriaRequest;
import com.example.restaurant.dtos.responses.CategoriaResponse;
import com.example.restaurant.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponse> crearCategoria(@RequestBody CategoriaRequest categoriaRequest) {
        CategoriaResponse nuevaCategoria = categoriaService.crearCategoria(categoriaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        List<CategoriaResponse> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obtenerCategoriaPorId(@PathVariable Long id) {
        CategoriaResponse categoria = categoriaService.obtenerCategoriaPorId(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> actualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequest categoriaRequest) {
        CategoriaResponse categoriaActualizada = categoriaService.actualizarCategoria(id, categoriaRequest);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
