package com.psethi.api.restshopping.controller;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.psethi.api.restshopping.dto.OrderDto;
import com.psethi.api.restshopping.model.OrderDetail;
import com.psethi.api.restshopping.model.Product;
import com.psethi.api.restshopping.model.User;
import com.psethi.api.restshopping.repository.ProductRepository;
import com.psethi.api.restshopping.repository.UserRepository;

/**
 * Junit test class for ShoppingController
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingControllerTest {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private ShoppingController shoppingController;

    /**
     * Load the product and user tables data for testing purpose
     * 
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        Product product = new Product();
        product.setId("1");
        product.setName("Samsung Monitor");
        product.setProductUnitAmount(new BigDecimal("100.99"));
        product.setTotalProductQuantity(50);
        productRepository.save(product);

        User user = new User();
        user.setId(1L);
        user.setAddress("9509 Key W Ave, Rockville, MD 20850");
        user.setName("Puneet Sethi");
        userRepository.save(user);

    }

    /**
     * Junit to test the placeOrder functionality
     * 
     * @throws Exception
     */
    @Test
    public void placeOrderTest() throws Exception {
        OrderDto orderDto = new OrderDto();
        orderDto.setProductId("1");
        orderDto.setQuantity(5);
        orderDto.setUserId(1L);
        orderDto.setCreditCardNumber("1234567812345678");

        ResponseEntity<OrderDetail> orderDetailResponse = shoppingController.placeOrder(orderDto);

        Assert.assertNotNull(orderDetailResponse);
        Assert.assertEquals(HttpStatus.ACCEPTED, orderDetailResponse.getStatusCode());
        Assert.assertEquals(new Long("1"), orderDetailResponse.getBody().getId());
        Assert.assertEquals(new Integer("5"), orderDetailResponse.getBody().getQuantity());
        Assert.assertEquals("Samsung Monitor", orderDetailResponse.getBody().getProduct().getName());
        Assert.assertEquals(new Integer("45"), orderDetailResponse.getBody().getProduct().getTotalProductQuantity());
        Assert.assertEquals("Puneet Sethi", orderDetailResponse.getBody().getUser().getName());
    }
}
