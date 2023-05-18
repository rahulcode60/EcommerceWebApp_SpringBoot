package com.example.demo.repository;

import javax.persistence.criteria.Order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
