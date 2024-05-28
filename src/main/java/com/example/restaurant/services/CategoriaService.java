package com.example.restaurant.services;

import com.example.restaurant.dtos.requests.CategoriaRequest;
import com.example.restaurant.dtos.responses.CategoriaResponse;
import com.example.restaurant.mappers.CategoriaMapper;
import com.example.restaurant.models.Categoria;
import com.example.restaurant.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Transactional
    public CategoriaResponse crearCategoria(CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaMapper.toCategoriaModel(categoriaRequest);
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaResponse(nuevaCategoria);
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toCategoriaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CategoriaResponse obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        return categoriaMapper.toCategoriaResponse(categoria);
    }

    @Transactional
    public CategoriaResponse actualizarCategoria(Long id, CategoriaRequest categoriaRequest) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        categoria.setNombre(categoriaRequest.getNombre());
        Categoria categoriaActualizada = categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaResponse(categoriaActualizada);
    }
}
