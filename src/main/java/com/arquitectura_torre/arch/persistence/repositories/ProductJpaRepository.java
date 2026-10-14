package com.arquitectura_torre.arch.persistence.repositories;

import com.arquitectura_torre.arch.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/***
 * Interfaz de Spring Data JPA. Pertenece exclusivamente a la capa de persistencia,
 * el dominio nunca la conoce: solo expone su propio contrato ProductRepository.
 */
public interface ProductJpaRepository extends JpaRepository<Product, Integer> {
}
