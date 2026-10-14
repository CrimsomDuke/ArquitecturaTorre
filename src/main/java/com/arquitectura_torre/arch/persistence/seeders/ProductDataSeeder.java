package com.arquitectura_torre.arch.persistence.seeders;

import com.arquitectura_torre.arch.domain.contracts.persistence.repositories.ProductRepository;
import com.arquitectura_torre.arch.domain.entities.Product;
import org.springframework.boot.CommandLineRunner;

/***
 * Siembra la tabla de products con datos de ejemplo cuando se encuentra vacia.
 * La creacion de la tabla la realiza Hibernate via spring.jpa.hibernate.ddl-auto=update.
 * El seeder usa unicamente el contrato de dominio, sin SQL explicito.
 */
public class ProductDataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    public ProductDataSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (!productRepository.GetAll().isEmpty()) {
            return;
        }

        productRepository.Create(new Product(0, "Teclado Mecanico", 75.50f));
        productRepository.Create(new Product(0, "Mouse Inalambrico", 25.99f));
        productRepository.Create(new Product(0, "Monitor 27 pulgadas", 310.00f));
    }
}
