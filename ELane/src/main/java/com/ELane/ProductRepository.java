package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByCategoryid(Integer categoryid);
    List<Product> findByProductid(Integer productid);
}