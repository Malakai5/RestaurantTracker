package connections;

import connections.databaseOperations.SQLWriter;
import foodItems.*;
import objects.Location;
import objects.Restaurant;

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
        testDrink.setRestaurantID(3);

        List<String> mainIngredients = new ArrayList<>();
        mainIngredients.add("Chocolate");
        mainIngredients.add("PeanutButter");
        mainIngredients.add("Ice cream");

        Dessert testDessert = new Dessert(mainIngredients);
        testDessert.setDairy(true);
        testDessert.setFavorite(true);
        testDessert.setName("Reese's Ice Cream Bar");

        Appetizer testAppetizer = new Appetizer("Shrimp", "Fried", false, tasteElements);
        testAppetizer.setName("Bang Bang Shrimp");

        Entree testEntree = new Entree("Dinner", 2,
                "Steak", "Pan-Seared",tasteElements);
        testEntree.setName("Garlic Butter Steak");
        testEntree.setDairy(true);
        testEntree.setMeat(true);
        testEntree.setHot(true);


//==========================TESTED METHODS THAT WORK======================
        SQLWriter.addNewLocation(testLocation);
        SQLWriter.addNewRestaurant(testRestaurant);
        System.out.println(SQLWriter.getLocation(3).toString());
        Restaurant wantedRest = SQLWriter.getRestaurant(1);
        SQLWriter.addNewDrink(testDrink);
        System.out.println(SQLWriter.getDrink(1).toString());
        System.out.println(SQLWriter.getDessert(4));
        SQLWriter.addNewDessert(testDessert);
        SQLWriter.addNewAppetizer(testAppetizer);
        System.out.println(SQLWriter.getAppetizer(5));
        SQLWriter.addNewEntree(testEntree);
        System.out.println(SQLWriter.getEntree(6));
    }
}
