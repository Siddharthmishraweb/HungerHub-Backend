package com.hungerhub.repository;

import com.hungerhub.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomerId(Long userId);

   // public List<Order> findRestaurantId(Long restaurantId);
    public List<Order> findByRestaurantId(Long restaurantId);


}
