package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByOrderid(int orderid);
    List<CartItem> findByProductid(int orderid);
}