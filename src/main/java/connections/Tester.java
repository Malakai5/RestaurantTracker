package connections;

import connections.databaseOperations.SQLWriter;
import foodItems.*;
import objects.Location;
import objects.Restaurant;
import objects.models.RestaurantSearchForm;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        Location testLocation = new Location
                ("FL","Tampa",33617,"Florida",1234, 23);
        Restaurant testRestaurant = new Restaurant("FoxTail", testLocation, "Bakery", "$", 3);

        List<Consumable.TasteElement> tasteElements = new ArrayList<>();
        tasteElements.add(Consumable.TasteElement.UMAMI);
        tasteElements.add(Consumable.TasteElement.SAVORY);
        Drink testDrink = new Drink(false, true);
        testDrink.setName("Maple Pancake Latte");
        testDrink.setTasteElements(tasteElements);
        testDrink.setDairy(true);
        testDrink.setRestaurantID(1);

        List<String> mainIngredients = new ArrayList<>();
        mainIngredients.add("Apple");
        mainIngredients.add("Piecrust");

        Dessert testDessert = new Dessert(mainIngredients);
        testDessert.setDairy(true);
        testDessert.setHot(true);
        testDessert.setFavorite(false);
        testDessert.setName("Apple Pie");
        testDessert.setRestaurantID(1);

        Appetizer testAppetizer = new Appetizer("Shrimp", "Fried", false, tasteElements);
        testAppetizer.setName("Bang Bang Shrimp");

        Entree testEntree = new Entree("Dinner", 2,
                "Steak", "Pan-Seared",tasteElements);
        testEntree.setName("Garlic Butter Steak");
        testEntree.setDairy(true);
        testEntree.setMeat(true);
        testEntree.setHot(true);

        RestaurantSearchForm form = new RestaurantSearchForm();
        form.restaurantName = "Bob Evans";
        form.city = "Tampa";
        form.state = "FL";
        form.priceRange = "$";
        form.foodType = "Breakfast";

        System.out.println(SQLWriter.getRestaurant(SQLWriter.searchForRestaurants(form).get(0)).toString());



//==========================TESTED METHODS THAT WORK======================
//        SQLWriter.getCities("FL").forEach(System.out::println);
//        SQLWriter.getColumn("food_type", "restaurant").forEach(System.out::println);
//        SQLWriter.addNewLocation(testLocation);
//        SQLWriter.addNewRestaurant(testRestaurant);
//        System.out.println(SQLWriter.getLocation(3).toString());
//        Restaurant wantedRest = SQLWriter.getRestaurant(1);
//        SQLWriter.addNewDrink(testDrink);
//        System.out.println(SQLWriter.getDrink(1).toString());
//        System.out.println(SQLWriter.getDessert(4));
//        SQLWriter.addNewDessert(testDessert);
//        SQLWriter.addNewAppetizer(testAppetizer);
//        System.out.println(SQLWriter.getAppetizer(5));
//        SQLWriter.addNewEntree(testEntree);
//        System.out.println(SQLWriter.getEntree(6));
    }
}
