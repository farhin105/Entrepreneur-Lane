package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {
    List<Payment> findByCustomerid(Integer customerid);
    List<Payment> findById(Integer id);
}