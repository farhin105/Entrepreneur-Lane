package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface CategoryProductRepository extends CrudRepository<CategoryProduct, Long> {
    List<CategoryProduct> findAll();
    List<CategoryProduct> findByCategoryname(String categoryname);
    List<CategoryProduct> findByCategoryid (Integer id);
}