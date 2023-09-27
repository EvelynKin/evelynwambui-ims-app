/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.equitytest.evelynwambui_ims_app.domain.entity.Product;
import com.equitytest.evelynwambui_ims_app.repository.ProductRepository;
import com.equitytest.evelynwambui_ims_app.service_impl.ProductServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceImplTest {

  @Mock
  private ProductRepository productRepository;

  private ProductServiceImpl productService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productService = new ProductServiceImpl(productRepository);
  }

  @Test
  public void testGetAllProducts() {
    // Mock data
    List<Product> productList = new ArrayList<>();
    //productList.add(new Product(1L, "Product1", "Description1", 10.0, false));
    //productList.add(new Product(2L, "Product2", "Description2", 20.0, false));

    when(productRepository.findAll()).thenReturn(productList);

    List<Product> result = productService.getAllProducts();

    assertEquals(2, result.size());
    assertEquals("Product1", result.get(0).getName());
    assertEquals("Product2", result.get(1).getName());
  }

  @Test
  public void testGetProductById_ExistingProduct() {
    // Mock data
    //Product product = new Product(1L, "Product1", "Description1", 10.0, false);

    //when(productRepository.findById(1L)).thenReturn(Optional.of(product));

    Product result = productService.getProductById(1L);

    assertEquals("Product1", result.getName());
  }

  @Test
  public void testGetProductById_NonExistingProduct() {
    when(productRepository.findById(1L)).thenReturn(Optional.empty());

    Product result = productService.getProductById(1L);

    assertEquals(null, result);
  }

  // Similar tests can be written for createProduct, updateProduct, and deleteProduct methods

  @Test
  public void testSearchProducts() {
    // Mock data
    List<Product> productList = new ArrayList<>();
    //productList.add(new Product(1L, "Product1", "Description1", 10.0, false));
    //productList.add(new Product(2L, "Product2", "Description2", 20.0, false));

    when(productRepository.findAllByNameContainingIgnoreCaseAndDeletedFalse("Product")).thenReturn(productList);

    List<Product> result = productService.searchProducts("Product");

    assertEquals(2, result.size());
    assertEquals("Product1", result.get(0).getName());
    assertEquals("Product2", result.get(1).getName());
  }
}
