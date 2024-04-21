package com.hungerhub.repository;

import com.hungerhub.model.Cart;
import com.hungerhub.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // public Cart findByCustomerId(Long userId);
    public Cart findByCartCustomerId(Long userId);

}
