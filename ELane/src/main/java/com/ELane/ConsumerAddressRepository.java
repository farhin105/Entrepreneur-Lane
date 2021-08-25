package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface ConsumerAddressRepository extends CrudRepository<ConsumerAddress, Long> {
    List<ConsumerAddress> findByConsumerid(Integer consumerid);
}