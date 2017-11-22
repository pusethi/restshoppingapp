package com.psethi.api.restshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.psethi.api.restshopping.model.User;

/**
 * Repository class for User entity
 * 
 * @author Puneet Kaur Sethi
 * @date Nov 21, 2017
 * @version 1.0
 */
@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Method to find user by user id
     */
    User findById(Long id);
}
