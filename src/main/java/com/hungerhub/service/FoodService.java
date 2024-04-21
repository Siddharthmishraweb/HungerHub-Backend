package com.hungerhub.service;

import com.hungerhub.model.Category;
import com.hungerhub.model.Food;
import com.hungerhub.model.Restaurant;
import com.hungerhub.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory);

    public List<Food> getAllFood(boolean isAll, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;

    public Food updateFood(Long foodId, CreateFoodRequest req) throws Exception;

}

























