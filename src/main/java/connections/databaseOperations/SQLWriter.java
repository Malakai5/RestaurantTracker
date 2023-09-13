package connections.databaseOperations;

import connections.Driver;
import objects.Location;
import objects.Restaurant;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SQLWriter {

    private static final NamedParameterJdbcTemplate namedParameterJdbcTemplate
            = new NamedParameterJdbcTemplate(Driver.getDataSource());

    public static LocationSQLOperations locationSQLOperations = new LocationSQLOperations(namedParameterJdbcTemplate);
    public static RestaurantSQLOperations restaurantSQLOperations = new RestaurantSQLOperations(namedParameterJdbcTemplate);

    public static void addNewLocation(Location location){
         locationSQLOperations.addNewLocation(location);
    }
    public static Location getLocation(int locationID){
        return locationSQLOperations.getLocation(locationID);
    }

    public static void addNewRestaurant(Restaurant restaurant){
        restaurantSQLOperations.addNewRestaurant(restaurant);
    }
    public static Restaurant getRestaurant(int restaurantID){
        Restaurant restaurant = restaurantSQLOperations.getRestaurant(restaurantID);
        System.out.println(restaurant.getLocationID());
        restaurant.setLocation(getLocation(restaurant.getLocationID()));
        return restaurant;
    }


}
