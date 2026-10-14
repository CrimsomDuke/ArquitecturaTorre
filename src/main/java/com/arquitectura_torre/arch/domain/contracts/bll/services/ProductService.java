package com.arquitectura_torre.arch.domain.contracts.bll.services;


public interface ProductService {
    /***
     * Calcular el precio de comprar un producto más el costo adicional de impuesto
     * @param productId
     * @return
     */
    float CalcularPrecioConImpuestos(int productId);
}
