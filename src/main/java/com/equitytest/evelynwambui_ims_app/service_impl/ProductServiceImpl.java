package com.equitytest.evelynwambui_ims_app.service_impl;


import com.equitytest.evelynwambui_ims_app.domain.entity.Product;
import com.equitytest.evelynwambui_ims_app.repository.ProductRepository;
import com.equitytest.evelynwambui_ims_app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            // Update the fields you want here
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(existingProduct);
        }
        return null; // Product not found
    }

    @Override
    public void deleteProduct(Long id) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            existingProduct.setDeleted(true); // Soft delete
            productRepository.save(existingProduct);
        }
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        // Implement search logic using custom query methods in the repository
        return productRepository.findAllByNameContainingIgnoreCaseAndDeletedFalse(keyword);
    }
}
