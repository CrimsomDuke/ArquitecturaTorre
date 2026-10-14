package com.arquitectura_torre.arch.bll.services;

import com.arquitectura_torre.arch.domain.contracts.bll.services.ProductService;
import com.arquitectura_torre.arch.domain.contracts.persistence.repositories.ProductRepository;
import com.arquitectura_torre.arch.domain.entities.Product;
import com.arquitectura_torre.arch.domain.exceptions.ProductNotFoundException;

public class ProductServiceImpl implements ProductService {

    private static final float TAX_RATE = 0.13f;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public float CalcularPrecioConImpuestos(int productId) {
        Product product = productRepository.GetById(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        return product.getPrice() * (1 + TAX_RATE);
    }
}
