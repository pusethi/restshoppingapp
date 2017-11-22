package com.psethi.api.restshopping.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity class for ORDER table primary key - order_id
 *
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({ @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false) })
    private Product Product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false) })
    private User user;

    @Column(name = "order_quantity")
    private Integer quantity;

    /**
     * Default Constructor
     */
    public OrderDetail() {
        super();
    }

    /**
     * Constructor with all fields
     * 
     * @param id
     * @param product
     * @param user
     * @param quantity
     */
    public OrderDetail(Long id, com.psethi.api.restshopping.model.Product product, User user, Integer quantity) {
        super();
        this.id = id;
        Product = product;
        this.user = user;
        this.quantity = quantity;
    }

    /**
     * @return the product
     */
    public Product getProduct() {
        return Product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(Product product) {
        Product = product;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

}