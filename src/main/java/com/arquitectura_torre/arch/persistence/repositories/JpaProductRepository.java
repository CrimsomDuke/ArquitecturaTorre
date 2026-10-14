package com.arquitectura_torre.arch.persistence.repositories;

import com.arquitectura_torre.arch.domain.contracts.persistence.repositories.ProductRepository;
import com.arquitectura_torre.arch.domain.entities.Product;

import java.util.List;

/***
 * Adaptador que implementa el contrato de dominio {@link ProductRepository}
 * delegando en Spring Data JPA. La capa de dominio y la de aplicacion solo
 * conocen el contrato, no esta implementacion ni JpaRepository.
 */
public class JpaProductRepository implements ProductRepository {

    private final ProductJpaRepository jpaRepository;

    public JpaProductRepository(ProductJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product GetById(int productId) {
        return jpaRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> GetAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Product Create(Product product) {
        return jpaRepository.save(product);
    }

    @Override
    public Product Update(Product product) {
        return jpaRepository.save(product);
    }

    @Override
    public void Delete(int productId) {
        jpaRepository.deleteById(productId);
    }
}
