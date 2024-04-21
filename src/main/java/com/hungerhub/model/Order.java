// package com.hungerhub.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import jakarta.persistence.*;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Table(name="orders")
// public class Order {
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Id
//     private Long id;

//     @ManyToOne
//     private User customer;

//     @ManyToOne
//     @JsonIgnore
//     private Restaurant restaurant;

//     private Long totalAmount;

//     private String orderStatus;

//     private Date createdAt;

//     @ManyToOne
//     private Address deliveryAddress;

//     @OneToMany
//     private List<OrderItem> items = new ArrayList<>();

//     //private Payment payment;

//     private Long totalItem;

//     private Long totalPrice;
// }


package com.hungerhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<OrderItem> items = new ArrayList<>();


    private Long totalItem;

    private Long totalPrice;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}
