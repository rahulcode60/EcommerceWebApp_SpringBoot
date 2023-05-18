package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ImageModel;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmailService emailService;
	
	

	//---------------------ADMIN------------------------------------------------
	

			//show all products admin ----> //http://localhost:8080/admin/showallProduct
		    @GetMapping("/getAllProducts") 
		    public List<Product> getAllProducts() {
		        return productService.getAllProducts();
		        
		    }
		
		    
		    //show prod by id admin ----> //http://localhost:8080/admin/getProductDeatilsById/{id}
		    @GetMapping("/getProductDetailsById/{id}")
		    public Product getProductById(@PathVariable Long id) {
		        return productService.getProductById(id);
		    }
		
		    
		    
//		    //Too  create new product or add Product --> //http://localhost:8080/admin/addproduct
//		    @PostMapping("/addproduct")
//		    public Product createProduct(@RequestBody Product product) {
//		    	System.out.println(product.toString());
//		        return productService.saveProduct(product);
//		    }
//		    
		

		    //Too  create new product or add Product --> //http://localhost:8080/admin/addproduct
		    @PostMapping(value ={"/addproduct"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
		    public Product createProduct(@RequestPart("product") Product product, @RequestPart("imageFile") MultipartFile[] file) {
		        
		    	try {
		    		Set<ImageModel> images =uploadImage(file);
		    		product.setProductImages(images);
		    		return productService.saveProduct(product);
		    		
		    	}
		    	catch(Exception e) {
		    		System.out.println(e.getMessage());
		    		return null;
		    	}
				 
		    	
		    	///return productService.saveProduct(product);
		    }
		   
		  //process images
		    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
		    	Set<ImageModel> imageModels = new HashSet<>();
		    	
		    	for(MultipartFile file:multipartFiles) {
		    		ImageModel imageModel = new ImageModel(
		    				file.getOriginalFilename(),
		    				file.getContentType(),
		    				file.getBytes());
		    		
		    		imageModels.add(imageModel);
		    		
		    		
		    	}
		    	return imageModels;
		    	
		    }
		
		    
	
		    
		    // To update the product  -----> http://localhost:8080/admin/updateProduct/{id}
		    @PutMapping("/updateProduct/{id}")
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
		    //http://localhost:8080/admin/deleteProductDetails/{id}
		    @DeleteMapping("/deleteProductDetails/{id}")
		    public void deleteProductDetails(@PathVariable Long id) {
		        
		    	// Call the ProductService to delete the product
		        boolean deleted = productService.deleteProduct(id);

		        if (deleted) {
		            //return ResponseEntity.ok("Product deleted successfully");
		        	System.out.println("Product deleted successfully");
		        } else {
		            ///return ResponseEntity.notFound().build();
		        	System.out.println("Product not deleted successfully");
		        	
		        }
		    }
		    
		    
		    
		    //to show orders of customers
		    //http://localhost:8080/admin/viewOrders
		    @GetMapping("/viewOrders")
		    public List<Orders> getAllOrders() {
		        return orderService.getAllOrders();		    }
		    
		    
		    
		    //-----------Analytics Part------------------//
		    
		    //http://localhost:8080/admin/viewOrders
		    @GetMapping("/sales") 
		    public double getTotalSales() {
		        return orderService.getTotalSales();
		    }
		    
		    
		    //http://localhost:8080/admin/salesCount
		    @GetMapping("/salesCount")
		    public int getTotalOrders() {
		        return orderService.count();
		    }
		    
    //-----------------------------------------------------//
    
    
    
}
