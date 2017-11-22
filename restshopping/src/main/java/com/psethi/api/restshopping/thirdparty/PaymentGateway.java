package com.psethi.api.restshopping.thirdparty;

import java.math.BigDecimal;

/**
 * Payment Gateway interface.
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
public interface PaymentGateway {

    boolean ChargePayment(String creditCardNumber, BigDecimal amount);
}
