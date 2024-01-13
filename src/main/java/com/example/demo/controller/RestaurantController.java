package com.example.demo.controller;

import com.example.demo.dto.RestaurantDto;
import com.example.demo.models.Restaurant;
import com.example.demo.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
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
    @GetMapping("/restaurant/{restaurantId}")
    public String RestaurentDetail(@PathVariable("restaurantId") long restaurantId ,Model model){
        RestaurantDto restaurantDto=restaurantService.findRestaurantById(restaurantId);
        model.addAttribute("restaurant",restaurantDto);
        return "restaurant-details";
    }

    @GetMapping("/restaurant/{restaurantId}/delete")
    public String DeleteRestaurent(@PathVariable("restaurantId") long restaurantId){
        restaurantService.delete(restaurantId);
        return "redirect:/restaurant";
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
    public String updateRestaurant(@PathVariable("restaurantId")long  restaurantId,
                                   @Valid @ModelAttribute("restaurant") RestaurantDto restaurant,
                                   BindingResult result) {
        if(result.hasErrors()){
            return "restaurants-edit";
        }
        restaurant.setId(restaurantId);
        restaurantService.updateRestaurant(restaurant);
        return "redirect:/restaurant";

    }


}
