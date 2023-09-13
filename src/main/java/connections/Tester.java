package connections;

import connections.databaseOperations.SQLWriter;
import objects.Location;
import objects.Restaurant;

public class Tester {
    public static void main(String[] args) {
        Location testLocation = new Location
                ("FL","Tampa",33617,"Florida",1234, 23);
        Restaurant testRestaurant = new Restaurant("FoxTail", testLocation, "Bakery", "$", 3);
//==========================TESTED METHODS THAT WORK======================
//        SQLWriter.addNewLocation(testLocation);
//        SQLWriter.addNewRestaurant(testRestaurant);
//        System.out.println(SQLWriter.getLocation(3).toString());
//        Restaurant wantedRest = SQLWriter.getRestaurant(1);
    }
}
