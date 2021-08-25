package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
    List<Consumer> findByUsernameAndPassword(String username, String password);
    List<Consumer> findByUsername(String username);
    List<Consumer> findAll();
    List<Consumer> findById(Integer id);

}