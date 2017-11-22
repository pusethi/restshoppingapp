package com.psethi.api.restshopping.dto;

/**
 * Dto class for OrderDto
 *
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */

public class OrderDto {

    private String productId;
    private long userId;
    private int quantity;
    private String creditCardNumber;

    /**
     * 
     */
    public OrderDto() {
        super();
    }

    /**
     * Constructor with all fields.
     * 
     * @param productId
     * @param userId
     * @param quantity
     * @param creditCardNumber
     */
    public OrderDto(String productId, long userId, int quantity, String creditCardNumber) {
        super();
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     *            the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber
     *            the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

}
