package com.psethi.api.restshopping.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.psethi.api.restshopping.dto.OrderDto;
import com.psethi.api.restshopping.model.OrderDetail;
import com.psethi.api.restshopping.model.Product;
import com.psethi.api.restshopping.model.User;
import com.psethi.api.restshopping.service.ShoppingService;

/**
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 2.0
 */
@RestController
@RequestMapping(value = "/restshopping")
public class ShoppingController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    private ShoppingService shoppingService;
    
    /**
     * Rest Service to get all Product records.
     * 
     * @return Product List
     */
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductRecords() {
        logger.info("******REST GET Webservice to get all product records.********");
        HttpStatus status = HttpStatus.OK;
        List<Product> productRecords = shoppingService.getAllProducts();
        if (productRecords == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<Product>>(productRecords, status);
    }
    
    /**
     * Rest Service to get all User records.
     * 
     * @return User List
     */
    @RequestMapping(value="/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUserRecords() {
        logger.info("******REST GET Webservice to get all user records.********");
        HttpStatus status = HttpStatus.OK;
        List<User> userRecords = shoppingService.getAllUsers();
        if (userRecords == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<User>>(userRecords, status);
    }
   
    /**
     * Rest Service to get all Order records.
     * 
     * @return Order List
     */
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> getOrderRecords() {
        logger.info("******REST GET Webservice to get all order records.********");
        HttpStatus status = HttpStatus.OK;
        List<OrderDetail> orderRecords = shoppingService.getAllOrders();
        if (orderRecords == null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<List<OrderDetail>>(orderRecords, status);
    }
    
    /**
     * Rest service to place order.
     * 
     * @param orderDto
     * @return OrderDetail ResponseEntity
     * @throws Exception 
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<OrderDetail> placeOrder(@RequestBody OrderDto orderDto) throws IllegalArgumentException {
        logger.info("\n******REST POST Webservice to place an order.********");
        logger.info("\nShopping Controller: Begin placeOrder ");
        HttpStatus status = HttpStatus.ACCEPTED;
        OrderDetail order = null;
        order = shoppingService.processOrder(orderDto);
        if (order != null)
        {
            status = HttpStatus.ACCEPTED;
            logger.info("\nOrder was successfully processed. Thank you for shopping. ");
        }        
        else
            throw new IllegalArgumentException("Error occured - Not able to place order");

        return new ResponseEntity<OrderDetail>(order, status);
    }
    
    
    
}
