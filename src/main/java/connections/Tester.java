package connections;

import connections.databaseOperations.SQLWriter;
import foodItems.Consumable;
import foodItems.Drink;
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
        tasteElements.add(Consumable.TasteElement.SWEET);
        tasteElements.add(Consumable.TasteElement.BITTER);
        Drink testDrink = new Drink(false, true);
        testDrink.setName("Maple Pancake Latte");
        testDrink.setTasteElements(tasteElements);
        testDrink.setDairy(true);

        SQLWriter.addNewDrink(testDrink);
        System.out.println(SQLWriter.getDrink(1).toString());

//==========================TESTED METHODS THAT WORK======================
//        SQLWriter.addNewLocation(testLocation);
//        SQLWriter.addNewRestaurant(testRestaurant);
//        System.out.println(SQLWriter.getLocation(3).toString());
//        Restaurant wantedRest = SQLWriter.getRestaurant(1);
    }
}
