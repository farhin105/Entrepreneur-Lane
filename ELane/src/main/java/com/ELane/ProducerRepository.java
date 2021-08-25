package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProducerRepository extends CrudRepository<Producer, Long> {
    List<Producer> findByUsernameAndPassword(String username, String password);
    List<Producer> findByUsername(String username);
    List<Producer>findAll();
    List<Producer> findById(Integer id);
}