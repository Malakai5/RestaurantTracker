package spring.controllers;

import connections.databaseOperations.SQLWriter;
import foodItems.Entree;
import foodItems.FoodItem;
import objects.Restaurant;
import objects.models.ConsumableEntryForm;
import objects.models.RestaurantEntryForm;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/entry")
public class EntryPageController {


    @RequestMapping(value = "/restaurantOptions", method = RequestMethod.GET)
    public List<Restaurant> getRestaurantList(@RequestParam(value = "name") String name) {
        System.out.println(name);
        return SQLWriter.getRestaurantList(name);
    }

    @PostMapping(value = "/restaurantEntry", consumes = "application/json", produces = "application/json")
    public void enterNewRestaurant(@RequestBody RestaurantEntryForm form){
        System.out.println(form.restaurantName);
        System.out.println(form.address);
        int locationId = SQLWriter.addNewLocation(form.constructLocation());
        SQLWriter.addNewRestaurant(form.constructRestaurant(locationId));
    }
    //TODO figure out restaurantName

    @PostMapping(value = "/consumableEntry", consumes = "application/json", produces = "application/json")
    public void enterNewConsumable(@RequestBody ConsumableEntryForm form){
        System.out.println(form.consumableName);
        System.out.println(form.restaurantName);
        FoodItem item = form.initializeFoodType();
        item = item.parseEntryForm(form);
//        SQLWriter.addNewConsumable(item);
    }

}
