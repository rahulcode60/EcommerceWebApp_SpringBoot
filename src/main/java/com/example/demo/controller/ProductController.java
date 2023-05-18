package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import java.io.IOException;
import java.util.*;
import com.example.demo.entity.ImageModel;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
    private ProductService productService;
	
	
	//------------------------USER---------------------------//
	
	
	
	//-----------------------------------------------------------//

	
	
	//---------------------ADMIN------------------------------------------------
	

			//show all products admin ----> //http://localhost:8080/products/showall
		    @GetMapping("Showall") 
		    public List<Product> getAllProducts() {
		        return productService.getAllProducts();
		    }
		
		    
		    //show prod by id admin ----> //http://localhost:8080/products/{id}
		    @GetMapping("/{id}")
		    public Product getProductById(@PathVariable Long id) {
		    	
		        return productService.getProductById(id);
		    }
		
		    
		    
//		    //Too  create new product or add Product --> //http://localhost:8080/products/addproduct
//		    @PostMapping(value ={"/addproduct"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
//		    public Product createProduct(@RequestPart("product") Product product, @RequestPart("imageFile") MultipartFile[] file) {
//		        
//		    	try {
//		    		Set<ImageModel> images =uploadImage(file);
//		    		product.setProductImages(images);
//		    		return productService.saveProduct(product);
//		    		
//		    	}
//		    	catch(Exception e) {
//		    		System.out.println(e.getMessage());
//		    		return null;
//		    	}
//				 
//		    	
//		    	///return productService.saveProduct(product);
//		    }
//		   
//		    
//		    //process images
//		    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//		    	Set<ImageModel> imageModels = new HashSet<>();
//		    	
//		    	for(MultipartFile file:multipartFiles) {
//		    		ImageModel imageModel = new ImageModel(
//		    				file.getOriginalFilename(),
//		    				file.getContentType(),
//		    				file.getBytes());
//		    		
//		    		imageModels.add(imageModel);
//		    		
//		    		
//		    	}
//		    	return imageModels;
//		    	
//		    }
		
		    
		    // To update the product  -----> http://localhost:8080/products/{id}
		    @PutMapping("/{id}")
		    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		        Product existingProduct = productService.getProductById(id);
		        if (existingProduct == null) {
		            return null;
		        }
		        existingProduct.setProductName(product.getProductName());
		        existingProduct.setProductDescription(product.getProductDescription());
		        existingProduct.setProductDiscountedPrice(product.getProductDiscountedPrice());
		        existingProduct.setProductActualPrice(product.getProductActualPrice());
		     
		        existingProduct.setProductCategory(product.getProductCategory());
		        return productService.saveProduct(existingProduct);
		    }
		
		    
		    //to delete product 
		    //http://localhost:8080/products/list/{id}
		    @DeleteMapping("/{id}")
		    public void deleteProduct(@PathVariable Long id) {
		        productService.deleteProduct(id);
		    }
		    
		    
		    
    //------------------------------------------------------------------------------------//
}
