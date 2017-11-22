package com.psethi.api.restshopping.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.psethi.api.restshopping.dto.OrderDto;
import com.psethi.api.restshopping.model.OrderDetail;
import com.psethi.api.restshopping.model.Product;
import com.psethi.api.restshopping.model.User;
import com.psethi.api.restshopping.repository.OrderDetailRepository;
import com.psethi.api.restshopping.repository.ProductRepository;
import com.psethi.api.restshopping.repository.UserRepository;
import com.psethi.api.restshopping.thirdparty.PaymentGateway;
import com.psethi.api.restshopping.util.ApplicationConstants;

/**
 * Service class for Shopping related operations.
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@Service("shoppingService")
public class ShoppingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Inject
    private ProductRepository productRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PaymentGateway paymentGateway;
    
    @Inject
    private OrderDetailRepository orderDetailRepository;

    @Inject
    public JavaMailSender emailSender;

    public OrderDetail processOrder(OrderDto order) {
        OrderDetail newOrder = null;
        logger.info("Checking the availability of the product in the inventory.");
        if (order != null) {
            //Check inventory
            if (checkInventory(order.getProductId(), order.getQuantity())) {
                logger.info("The product is available in the inventory.");
                //Validate credit card
                if (validateCreditCardNumber(order.getCreditCardNumber())) {
                    Product product = productRepository.findById(order.getProductId());
                    logger.info("Payment is being processed through the payment gateway.");
                    if (product != null) {
                        //charge payment
                        if (paymentGateway.ChargePayment(order.getCreditCardNumber(),
                                (product.getProductUnitAmount().multiply(new BigDecimal(order.getQuantity()))))) {
                            logger.info("Payment Gateway authorized the payment. The order is successfully processed.");
                            product.setTotalProductQuantity(product.getTotalProductQuantity() - order.getQuantity());
                            //Update inventory
                            productRepository.saveAndFlush(product);
                            logger.info("The product quantity is sucessfully updated in the database.");
                            
                            //Save order record
                            newOrder = prepareOrder(order);
                            
                            //Process shipping when the order is done
                            if(newOrder != null) {
                                if (!processOrderShipping(newOrder)) {
                                    logger.info("Error while sending an email to the shipping company.");
                                }  
                            }
                            
                        } 
                    }
                }
            }
        } 

        return newOrder;
    }

    /**
     * Helper method to create an entry in OrderDetail table for the new order.
     * @param order
     * @return newOrder
     */
    private OrderDetail prepareOrder(OrderDto orderDto) {
        logger.info("Begin: prepareOrder");
        OrderDetail order = new OrderDetail();
        OrderDetail newOrder = null;
        if (orderDto != null) {
            // find User
            User user = userRepository.findById(orderDto.getUserId());
            // find Product
            Product product = productRepository.findById(orderDto.getProductId());
            if (user != null && product != null) {
                order = new OrderDetail();
                order.setUser(user);
                order.setProduct(product);
                order.setQuantity(orderDto.getQuantity());
                newOrder = orderDetailRepository.saveAndFlush(order);
            }
        }

        return newOrder;
    }

    /**
     * Helper method to determine credit card is valid or not. Valid card if of 16
     * digits and contains only numbers.
     * 
     * @param creditCardNumber
     * @return boolean
     */
    private boolean validateCreditCardNumber(String creditCardNumber) {
        boolean isValid = false;
        if (creditCardNumber.matches("[0-9]+")) {
            logger.info("Credit card contains only numbers");
        }
        if (creditCardNumber != null && creditCardNumber.matches("[0-9]+") && creditCardNumber.length() == 16) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * Helper method to check for product inventory.
     * 
     * @param productId
     * @param qty
     * @return boolean
     */
    private boolean checkInventory(String productId, int qty) {
        boolean isPresent = false;
        if(productId != null && qty > 0) {
            Product product = productRepository.findById(productId);  
            if(product != null) {
                if (product.getTotalProductQuantity().intValue() < qty) {
                    logger.info("The product quantity is not available in the inventory.");
                    isPresent = false;
                } else {
                    logger.info("The product quantity is available in the inventory.");
                    isPresent = true;
                }   
            }
            
        }
        return isPresent;
    }

    /**
     * Helper method to process shipping
     * @param order
     * @return boolean
     */
    private boolean processOrderShipping(OrderDetail order) {
        boolean shippingProcessed = true;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(ApplicationConstants.SHIPPING_EMAIL_ADDRESS);
        message.setSubject(ApplicationConstants.SHIPPING_EMAIL_SUBJECT);
        message.setText(ApplicationConstants.SHIPPING_EMAIL_BODY + order.getId());
        try {
            emailSender.send(message);
        } catch (MailException e) {
            logger.error("Error occured while sending an email to the shipping company. ");
            shippingProcessed = false;
        }
        return shippingProcessed;
    }

    /**
     * Rest Service to get all Product record.
     * 
     * @return Products List
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Rest Service to get all User record.
     * 
     * @return Users List
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Rest Service to get all Order record.
     * 
     * @return Orders List
     */
    public List<OrderDetail> getAllOrders() {
        return orderDetailRepository.findAll();
    }

}
