package spring.controllers;

import connections.databaseOperations.SQLWriter;
import foodItems.Consumable;
import objects.Restaurant;
import objects.models.RestaurantSearchForm;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:63342")
@RestController()
@RequestMapping("/filter")
public class FilterOptionsController {

    @RequestMapping(value = "/foodType", method = RequestMethod.GET)
    public List<String> getFoodTypeOptions() {
        return SQLWriter.getColumn("food_type", "restaurant");
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<String> getCityOptions(@RequestParam(value = "state", defaultValue = "EMPTY") String state) {
        return SQLWriter.getCities(state);
    }

    @PostMapping(value = "/restaurantSearchForm", consumes = "application/json", produces = "application/json")
    public List<Restaurant> consumeRestaurantSearchForm(@RequestBody RestaurantSearchForm form){
        List<Integer> restaurantIds = SQLWriter.searchForRestaurants(form);
        System.out.println(restaurantIds);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantIds.forEach(id -> restaurants.add(SQLWriter.getRestaurant(id)));
        return restaurants;
    }

//    @PostMapping(value = "/consumableSearchForm", consumes = "application/json", produces = "application/json")
//    public List<Consumable> consumeConsumableSearchForm(@RequestBody)
}
