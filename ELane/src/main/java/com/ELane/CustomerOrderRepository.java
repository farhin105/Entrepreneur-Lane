package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
    List<CustomerOrder> findByPaymentid(int paymentid);
    List<CustomerOrder> findById(int id);
}