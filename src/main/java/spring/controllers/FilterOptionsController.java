package spring.controllers;

import connections.databaseOperations.SQLWriter;
import foodItems.Consumable;
import foodItems.Entree;
import foodItems.FoodItem;
import objects.Restaurant;
import objects.models.ConsumableSearchForm;
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
    public List<Restaurant> consumeSearchForm(@RequestBody RestaurantSearchForm form){
        List<Integer> restaurantIds = SQLWriter.processSearchRequest(form);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantIds.forEach(id -> restaurants.add(SQLWriter.getRestaurant(id)));
        return restaurants;
    }
    @PostMapping(value = "/consumableSearchForm", consumes = "application/json", produces = "application/json")
    public List<FoodItem> consumeSearchForm(@RequestBody ConsumableSearchForm form){
        List<Integer> consumableIds = SQLWriter.processSearchRequest(form);
        List<FoodItem> consumables = new ArrayList<>();
        consumableIds.forEach(id -> consumables.add(SQLWriter.getConsumable(id)));
        return consumables;
    }

    @PostMapping(value = "/simpleTest", produces = "application/json")
    public Entree simpleTest(){
        List<Consumable.TasteElement> tasteElements = new ArrayList<>();
        tasteElements.add(Consumable.TasteElement.SAVORY);
        tasteElements.add(Consumable.TasteElement.UMAMI);
        tasteElements.add(Consumable.TasteElement.SALTY);

        Entree testEntree = new Entree("Lunch", 0,
                "Tuna", "Sushi",tasteElements);
        testEntree.setName("Rainbow Tuna Roll");
        testEntree.setDairy(false);
        testEntree.setMeat(true);
        testEntree.setHot(false);
        testEntree.setPrice(22.00);
        testEntree.setFavorite(true);
        testEntree.setRestaurantID(3);
        return testEntree;
    }
}
