package com.example.restaurant.mappers;

import com.example.restaurant.dtos.requests.CategoriaRequest;
import com.example.restaurant.dtos.responses.CategoriaResponse;
import com.example.restaurant.models.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria mapToCategoriaModel(CategoriaRequest categoriaRequest) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaRequest.getNombre());
        return categoria;
    }

    public CategoriaResponse mapToCategoriaResponse(Categoria categoria) {
        CategoriaResponse categoriaResponse = new CategoriaResponse();
        categoriaResponse.setId(categoria.getId());
        categoriaResponse.setNombre(categoria.getNombre());
        return categoriaResponse;
    }

    public void updateCategoriaFromRequest(CategoriaRequest categoriaRequest, Categoria categoria) {
        categoria.setNombre(categoriaRequest.getNombre());
    }
}
