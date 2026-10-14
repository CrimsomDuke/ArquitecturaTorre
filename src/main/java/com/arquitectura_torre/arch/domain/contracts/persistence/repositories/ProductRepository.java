package com.arquitectura_torre.arch.domain.contracts.persistence.repositories;

import com.arquitectura_torre.arch.domain.entities.Product;

import java.util.List;

public interface ProductRepository {
    Product GetById(int productId);
    List<Product> GetAll();
    Product Create(Product product);
    Product Update (Product product);
    void Delete(int productId);
}
