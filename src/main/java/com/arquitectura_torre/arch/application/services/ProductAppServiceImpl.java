package com.arquitectura_torre.arch.application.services;

import com.arquitectura_torre.arch.application.abstraction.services.ProductAppService;
import com.arquitectura_torre.arch.application.dtos.product.ProductDTO;
import com.arquitectura_torre.arch.application.dtos.product.ProductPriceWithTaxesDTO;
import com.arquitectura_torre.arch.application.mappers.product.ProductMapper;
import com.arquitectura_torre.arch.domain.contracts.bll.services.ProductService;
import com.arquitectura_torre.arch.domain.contracts.persistence.repositories.ProductRepository;
import com.arquitectura_torre.arch.domain.entities.Product;
import com.arquitectura_torre.arch.domain.exceptions.ProductNotFoundException;

import java.util.List;

public class ProductAppServiceImpl implements ProductAppService {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductAppServiceImpl(ProductRepository productRepository,
                                 ProductService productService,
                                 ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> GetAll() {
        return productRepository.GetAll().stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Override
    public ProductDTO GetById(int productId) {
        Product product = productRepository.GetById(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        return productMapper.toDto(product);
    }

    @Override
    public ProductDTO Create(ProductDTO dto) {
        validate(dto);
        Product created = productRepository.Create(productMapper.toEntity(dto));
        return productMapper.toDto(created);
    }

    @Override
    public ProductDTO Update(int productId, ProductDTO dto) {
        validate(dto);
        if (productRepository.GetById(productId) == null) {
            throw new ProductNotFoundException(productId);
        }
        Product product = productMapper.toEntity(dto);
        product.setId(productId);
        return productMapper.toDto(productRepository.Update(product));
    }

    @Override
    public void Delete(int productId) {
        if (productRepository.GetById(productId) == null) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.Delete(productId);
    }

    @Override
    public ProductPriceWithTaxesDTO GetPriceWithTaxes(int productId) {
        Product product = productRepository.GetById(productId);
        if (product == null) {
            throw new ProductNotFoundException(productId);
        }
        float priceWithTaxes = productService.CalcularPrecioConImpuestos(productId);
        return new ProductPriceWithTaxesDTO(productId, product.getPrice(), priceWithTaxes);
    }

    private void validate(ProductDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }
        if (dto.getPrice() < 0) {
            throw new IllegalArgumentException("El precio del producto no puede ser negativo");
        }
    }
}
