package com.hungerhub.controller;

import com.hungerhub.model.Food;
import com.hungerhub.model.User;
import com.hungerhub.service.FoodService;
import com.hungerhub.service.RestaurantService;
import com.hungerhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt)
            throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(
            @RequestParam boolean isVegetarian,
            @RequestParam boolean isSeasonal,
            @RequestParam boolean isNonVeg,
            @RequestParam(required = false) String food_category,
            @PathVariable Long restaurantId,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, isVegetarian, isNonVeg, isSeasonal,
                food_category);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Food>> getAllFood(
           @RequestHeader("Authorization") String jwt,
            @RequestParam boolean isAll,
            @RequestParam boolean isVegetarian,
            @RequestParam boolean isSeasonal,
            @RequestParam boolean isNonVeg) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getAllFood(isAll, isVegetarian, isNonVeg, isSeasonal);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}