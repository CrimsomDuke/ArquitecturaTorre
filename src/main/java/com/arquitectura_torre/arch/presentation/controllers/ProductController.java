package com.arquitectura_torre.arch.presentation.controllers;

import com.arquitectura_torre.arch.application.abstraction.services.ProductAppService;
import com.arquitectura_torre.arch.application.dtos.product.ProductDTO;
import com.arquitectura_torre.arch.application.dtos.product.ProductPriceWithTaxesDTO;
import com.arquitectura_torre.arch.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/***
 * Capa de presentacion. Solo conoce la capa de aplicacion (ProductAppService)
 * y el dominio (ProductNotFoundException).
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductAppService productAppService;

    public ProductController(ProductAppService productAppService) {
        this.productAppService = productAppService;
    }

    @GetMapping
    public List<ProductDTO> GetAll() {
        return productAppService.GetAll();
    }

    @GetMapping("/{id}")
    public ProductDTO GetById(@PathVariable int id) {
        return productAppService.GetById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO Create(@RequestBody ProductDTO dto) {
        return productAppService.Create(dto);
    }

    @PutMapping("/{id}")
    public ProductDTO Update(@PathVariable int id, @RequestBody ProductDTO dto) {
        return productAppService.Update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Delete(@PathVariable int id) {
        productAppService.Delete(id);
    }

    @GetMapping("/{id}/price-with-taxes")
    public ProductPriceWithTaxesDTO GetPriceWithTaxes(@PathVariable int id) {
        return productAppService.GetPriceWithTaxes(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> HandleNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> HandleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", ex.getMessage()));
    }
}
