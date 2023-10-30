package connections.databaseOperations;

import connections.Driver;
import foodItems.*;
import objects.Location;
import objects.Restaurant;
import objects.models.ConsumableSearchForm;
import objects.models.RestaurantSearchForm;
import objects.models.SearchForm;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class SQLWriter {

    private static final NamedParameterJdbcTemplate queryParameter
            = new NamedParameterJdbcTemplate(Driver.getDataSource());
    public static LocationSQLOperations locationSQLOperations = new LocationSQLOperations(queryParameter);
    public static RestaurantSQLOperations restaurantSQLOperations = new RestaurantSQLOperations(queryParameter);
    public static ConsumableSQLOperations consumableSQLOperations = new ConsumableSQLOperations(queryParameter);
    public static DatabaseSearchOperations databaseSearchOperations = new DatabaseSearchOperations(queryParameter);

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
        restaurant.setLocation(getLocation(restaurant.getLocationID()));
        return restaurant;
    }
    public static void addNewConsumable(FoodItem consumable){
        consumableSQLOperations.addNewConsumable(consumable);
    }
    public static FoodItem getConsumable(int consumableID){
       return consumableSQLOperations.getConsumable(consumableID);
    }

    public static List<String> getColumn(String columnName, String wantedTable){
        return databaseSearchOperations.getColumn(columnName, wantedTable);
    }
    public static List<String> getCities(String state){
        return databaseSearchOperations.getCities(state);
    }

    public static List<Integer> processSearchRequest(SearchForm form){
        return databaseSearchOperations.processSearchRequest(form);
    }




}
