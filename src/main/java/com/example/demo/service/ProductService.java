package com.example.demo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
    
    public boolean deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);
            return true;
        }
        
        return false;
    }

	public void saveProduct(String productName, String productDescription, double productActualPrice,
			double productDiscountedPrice, byte[] productImage, ProductCategory productCategoryId) {
		// Create a new Product entity and set its properties
		Product product = new Product();
		product.setProductName(productName);
		product.setProductDescription(productDescription);
		product.setProductActualPrice(productActualPrice);
		product.setProductDiscountedPrice(productDiscountedPrice);
		product.setProductCategory(productCategoryId);

		// Set the product image
	

		// Save the product
		productRepository.save(product);
    }
  }
	

	

