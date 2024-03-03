package connections;

import connections.databaseOperations.SQLWriter;
import foodItems.*;
import objects.Location;
import objects.Restaurant;
import objects.models.ConsumableSearchForm;
import objects.models.RestaurantSearchForm;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) {
        Location testLocation = new Location
                ("FL","Tampa",33610,"Nebraska",0123, 0);
        Restaurant testRestaurant = new Restaurant("Nebraska MiniMart", testLocation, "Caribbean", "$", 1);

        List<Consumable.TasteElement> tasteElements = new ArrayList<>();
        tasteElements.add(Consumable.TasteElement.SAVORY);
        tasteElements.add(Consumable.TasteElement.UMAMI);
        tasteElements.add(Consumable.TasteElement.SALTY);
        Drink testDrink = new Drink(true, false);
        testDrink.setName("Deep Red Wine");
        testDrink.setTasteElements(tasteElements);
        testDrink.setDairy(false);
        testDrink.setPrice(21.00);
        testDrink.setRestaurantID(1);

        List<String> mainIngredients = new ArrayList<>();
        mainIngredients.add("Vanilla Ice Cream");
        mainIngredients.add("Chocolate");

        Dessert testDessert = new Dessert(mainIngredients);
        testDessert.setDairy(true);
        testDessert.setHot(false);
        testDessert.setFavorite(false);
        testDessert.setName("Ice Cream Sunday");
        testDessert.setRestaurantID(1);
        testDessert.setPrice(14.50);

        Appetizer testAppetizer = new Appetizer("Cheese", "Baked", true, tasteElements);
        testAppetizer.setName("SteakHouse Mac and Cheese");
        testAppetizer.setPrice(19);
        testAppetizer.setHot(true);
        testAppetizer.setSpicy(false);
        testAppetizer.setMeat(true);
        testAppetizer.setDairy(true);
        testAppetizer.setRestaurantID(1);

        Entree testEntree = new Entree("Dinner", 2,
                "Wagyu Beef", "Grill",tasteElements);
        testEntree.setName("Big ol BURGER");
        testEntree.setDairy(true);
        testEntree.setMeat(true);
        testEntree.setHot(false);
        testEntree.setPrice(29.00);
        testEntree.setFavorite(true);
        testEntree.setRestaurantID(1);

        RestaurantSearchForm form = new RestaurantSearchForm();
        form.restaurantName = "Bob Evans";
        form.city = "Tampa";
        form.state = "FL";
        form.priceRange = "$";
        form.foodType = "Breakfast";

        ConsumableSearchForm conForm = new ConsumableSearchForm();
        conForm.consumableType = "Entree";
        conForm.consumableName = "";
        conForm.mainTasteElement = "";
        conForm.timeOfMeal = "Breakfast";

//        SQLWriter.addNewLocation(testLocation);
//        SQLWriter.addNewRestaurant(testRestaurant);
        SQLWriter.addNewConsumable(testEntree);
    }
}
