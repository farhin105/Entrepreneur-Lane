package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Laboni on 5/1/2017.
 */
public interface FarmAddressRepository extends CrudRepository<FarmAddress, Long> {
    List<FarmAddress> findAll();
    List<FarmAddress> findByProducerid(int producerid);
}
