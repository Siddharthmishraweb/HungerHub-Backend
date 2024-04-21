package com.hungerhub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hungerhub.dto.RestaurantDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String fullName;

    private String email;

    private String password;

    private USER_ROLE role = USER_ROLE.ROLE_CUSTOMER;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();




    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses= new ArrayList<>();

    public USER_ROLE getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullname){
        this.fullName = fullname;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavorites(List<RestaurantDto> favorites) {
        this.favorites = favorites;
    }
    public List<RestaurantDto> getFavorites() {
        return favorites;
    }

    public Long getId() {
        return id;
    }
}
