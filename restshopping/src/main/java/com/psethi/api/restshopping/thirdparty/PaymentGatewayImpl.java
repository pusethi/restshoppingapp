package com.psethi.api.restshopping.thirdparty;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/**
 * Implementation class for the payment gateway. Note: Because of time
 * constraint to implement this coding assignment I am assuming payment gateway
 * will return true for simplicity.
 * 
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@Service
public class PaymentGatewayImpl implements PaymentGateway {

    public boolean ChargePayment(String creditCardNumber, BigDecimal amount) {
        return true;
    }
}
