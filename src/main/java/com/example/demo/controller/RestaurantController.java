package com.example.demo.controller;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.models.Restaurant;
import com.example.demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class RestaurantController {
        private RestaurantService restaurantService;



    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurant")
    public String Listsrestaurants(Model model){
        List<RestaurantDto> restaurants=restaurantService.findAllResto();
        model.addAttribute("restaurants",restaurants);
        return "restaurants-lists";
    }
    @GetMapping("/restaurant/new")
    public String cretaterestaurantMenu(Model model){
        Restaurant restaurant=new Restaurant();
        model.addAttribute("restaurant",restaurant);
        return "Menu-create";
    }
    @PostMapping("/restaurant/new")
    public String saveRestaurant(@ModelAttribute("restaurant")Restaurant restaurant){
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurant";
}
    @GetMapping("/restaurant/{restaurantId}/edit")
    public String editRestaurantForm(@PathVariable("restaurantId") long  restaurantId,Model model){
        RestaurantDto restaurant =restaurantService.findRestaurantById(restaurantId);
        model.addAttribute("restaurant",restaurant);
        return "restaurants-edit";
    }
    @PostMapping("/restaurant/{restaurantId}/edit")
    public String updateRestaurant(@PathVariable("restaurantId")long  restaurantId,@ModelAttribute("restaurant") RestaurantDto restaurant) {
        restaurant.setId(restaurantId);
        restaurantService.updateRestaurant(restaurant);
        return "redirect:/restaurant";

    }


}
