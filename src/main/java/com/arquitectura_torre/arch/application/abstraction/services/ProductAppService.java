package com.arquitectura_torre.arch.application.abstraction.services;

import com.arquitectura_torre.arch.application.dtos.product.ProductDTO;
import com.arquitectura_torre.arch.application.dtos.product.ProductPriceWithTaxesDTO;

import java.util.List;

public interface ProductAppService {
    List<ProductDTO> GetAll();
    ProductDTO GetById(int productId);
    ProductDTO Create(ProductDTO dto);
    ProductDTO Update(int productId, ProductDTO dto);
    void Delete(int productId);
    ProductPriceWithTaxesDTO GetPriceWithTaxes(int productId);
}
