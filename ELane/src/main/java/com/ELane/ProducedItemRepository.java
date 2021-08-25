package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface ProducedItemRepository extends CrudRepository<ProducedItem, Long> {
    List<ProducedItem> findByProductid(Integer productid);
    List<ProducedItem> findByProducerid(int id);
}