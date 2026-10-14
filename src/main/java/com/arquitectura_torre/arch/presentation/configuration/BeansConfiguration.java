package com.arquitectura_torre.arch.presentation.configuration;

import com.arquitectura_torre.arch.application.abstraction.services.ProductAppService;
import com.arquitectura_torre.arch.application.mappers.product.ProductMapper;
import com.arquitectura_torre.arch.application.services.ProductAppServiceImpl;
import com.arquitectura_torre.arch.bll.services.ProductServiceImpl;
import com.arquitectura_torre.arch.domain.contracts.bll.services.ProductService;
import com.arquitectura_torre.arch.domain.contracts.persistence.repositories.ProductRepository;
import com.arquitectura_torre.arch.persistence.repositories.JpaProductRepository;
import com.arquitectura_torre.arch.persistence.repositories.ProductJpaRepository;
import com.arquitectura_torre.arch.persistence.seeders.ProductDataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    // ---- Capa de Persistencia ----

    @Bean
    public ProductRepository productRepository(ProductJpaRepository jpaRepository) {
        return new JpaProductRepository(jpaRepository);
    }

    @Bean
    public CommandLineRunner dataSeeder(ProductRepository productRepository) {
        return new ProductDataSeeder(productRepository);
    }

    // ---- Capa de BLL ----

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    // ---- Capa de Aplicacion ----

    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }

    @Bean
    public ProductAppService productAppService(ProductRepository productRepository,
                                               ProductService productService,
                                               ProductMapper productMapper) {
        return new ProductAppServiceImpl(productRepository, productService, productMapper);
    }
}
