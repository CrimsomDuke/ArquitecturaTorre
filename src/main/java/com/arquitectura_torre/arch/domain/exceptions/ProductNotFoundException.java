package com.arquitectura_torre.arch.domain.exceptions;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int productId) {
        super("El producto con id " + productId + " no existe");
    }
}
