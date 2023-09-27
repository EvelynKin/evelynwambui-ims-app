package com.equitytest.evelynwambui_ims_app.service;

import com.equitytest.evelynwambui_ims_app.domain.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    List<Product> searchProducts(String keyword);
    // Add other custom methods for filtering as needed
}
