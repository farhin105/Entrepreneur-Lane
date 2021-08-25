package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Laboni on 5/1/2017.
 */
public interface ProducerAddressRepository extends CrudRepository<ProducerAddress, Long> {
    List<ProducerAddress> findByPkey(int pkey);
    List<ProducerAddress> findByProducerid(int producerid);
}
