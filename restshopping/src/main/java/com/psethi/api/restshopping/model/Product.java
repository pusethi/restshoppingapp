package com.psethi.api.restshopping.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class for PRODUCT table primary key - product_id
 *
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", nullable = false)
    private String id;

    @Column(name = "product_name", length = 50, nullable = false)
    private String name;

    @Column(name = "total_product_quantity")
    private Integer totalProductQuantity;

    @Column(name = "product_unit_amt", precision = 15, scale = 2, nullable = false)
    private BigDecimal productUnitAmount;

    /**
     * Default Constructor
     */
    public Product() {
        super();
    }

    /**
     * Constructor with all fields
     * 
     * @param id
     * @param name
     * @param totalProductQuantity
     * @param productUnitAmount
     */
    public Product(String id, String name, Integer totalProductQuantity, BigDecimal productUnitAmount) {
        super();
        this.id = id;
        this.name = name;
        this.totalProductQuantity = totalProductQuantity;
        this.productUnitAmount = productUnitAmount;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the totalProductQuantity
     */
    public Integer getTotalProductQuantity() {
        return totalProductQuantity;
    }

    /**
     * @param totalProductQuantity
     *            the totalProductQuantity to set
     */
    public void setTotalProductQuantity(Integer totalProductQuantity) {
        this.totalProductQuantity = totalProductQuantity;
    }

    /**
     * @return the productUnitAmount
     */
    public BigDecimal getProductUnitAmount() {
        return productUnitAmount;
    }

    /**
     * @param productUnitAmount
     *            the productUnitAmount to set
     */
    public void setProductUnitAmount(BigDecimal productUnitAmount) {
        this.productUnitAmount = productUnitAmount;
    }

}
