package com.example.demo.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Orders;
import com.example.demo.entity.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Service
public class OrderService {

	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	 @Autowired
	 private EmailService emailService;

	
	public OrderService(OrderRepository orderRepository, UserRepository userRepository, EmailService emailService) {
		super();
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.emailService = emailService;
	}


	public List<Orders> getAllOrders() {
        return orderRepository.findAll();  
       }


	public double getTotalSales() {
		// TODO Auto-generated method stub
		 List<Orders> orders = orderRepository.findAll();
	        double totalSales = 0.0;
	        for (Orders order : orders) {
	            totalSales += order.getTotalAmount();
	        }
	        return totalSales;
	}


	public int count() {
		// TODO Auto-generated method stub
		return (int) orderRepository.count();
	}	
	

	public void sendOrderConfirmationEmail(Orders order) {
        User u = order.getUser();
        String to = "rahulpalve408@gmail.com";
        String subject = "Order Confirmation";
        String body = "Dear " + u.getUserName() + ",\n\n" +
                      "Thank you for your order. Your order has been confirmed.\n\n" +
                      "Order ID: " + order.getOrderId() + "\n" +
                      "Total Amount: " + order.getTotalAmount() + "\n\n" +
                      "Thank you for shopping with us.";

        emailService.sendEmail(to, subject, body);
    }
	
	
	public Orders findById(int id) {
	    Optional<Orders> optionalOrders = orderRepository.findById((long) id);
	    return optionalOrders.orElse(null); // or throw an exception if desired
	}

}
