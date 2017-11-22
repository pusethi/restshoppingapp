package com.psethi.api.restshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psethi.api.restshopping.model.OrderDetail;

/**
 * Repository class for Order Detail entity
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@Transactional
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
}
