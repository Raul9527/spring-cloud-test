package com.lrg.service;

import com.lrg.service.bean.Product;

public class ProductService implements IProductService{

    public Product getProductByID(Long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("hehe");
        product.setPrice(99.99);
        return null;
    }
}
