package com.psethi.api.restshopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.psethi.api.restshopping.model.Product;

/**
 * Repository class for Product entity
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@Transactional
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    /**
     * Method to find product by product id
     */
    Product findById(String productId);  
    
    /**
     * Method to find all products 
     */
    List<Product> findAll();
}
