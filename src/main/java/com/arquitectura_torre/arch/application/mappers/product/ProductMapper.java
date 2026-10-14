package com.arquitectura_torre.arch.application.mappers.product;

import com.arquitectura_torre.arch.application.dtos.product.ProductDTO;
import com.arquitectura_torre.arch.domain.entities.Product;

public class ProductMapper {

    public ProductDTO toDto(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    public Product toEntity(ProductDTO dto) {
        if (dto == null) {
            return null;
        }
        int id = dto.getId() == null ? 0 : dto.getId();
        return new Product(id, dto.getName(), dto.getPrice());
    }
}
