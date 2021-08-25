package com.ELane;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ASUS on 17-Apr-17.
 */
public interface NotificationRepository extends CrudRepository<Notification, Long> {
    List<Notification> findByToid (Integer toid);
    List<Notification> findAll();
}