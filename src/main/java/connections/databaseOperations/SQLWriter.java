package connections.databaseOperations;

import connections.Driver;
import foodItems.Appetizer;
import foodItems.Dessert;
import foodItems.Drink;
import foodItems.Entree;
import objects.Location;
import objects.Restaurant;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class SQLWriter {

    private static final NamedParameterJdbcTemplate queryParameter
            = new NamedParameterJdbcTemplate(Driver.getDataSource());

    public static LocationSQLOperations locationSQLOperations = new LocationSQLOperations(queryParameter);
    public static RestaurantSQLOperations restaurantSQLOperations = new RestaurantSQLOperations(queryParameter);
    public static ConsumableSQLOperations consumableSQLOperations = new ConsumableSQLOperations(queryParameter);

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

    public static void addNewDrink(Drink drink){
        consumableSQLOperations.addNewDrink(drink);
    }
    public static Drink getDrink(int drinkID){
        return consumableSQLOperations.getDrink(drinkID);
    }

    public static void addNewDessert(Dessert dessert){
        consumableSQLOperations.addNewDessert(dessert);
    }
    public static Dessert getDessert(int dessertID){
        return consumableSQLOperations.getDessert(dessertID);
    }


    public static void addNewAppetizer(Appetizer appetizer){
        consumableSQLOperations.addNewAppetizer(appetizer);
    }
    public static Appetizer getAppetizer(int appetizerID){
       return consumableSQLOperations.getAppetizer(appetizerID);
    }


    public static void addNewEntree(Entree entree){
        consumableSQLOperations.addNewEntree(entree);
    }
    public static Entree getEntree(int entreeID){
        return consumableSQLOperations.getEntree(entreeID);
    }

    public static List<String> getColumn(String columnName, String wantedTable){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.column");
        sqlQuery = sqlQuery.replace("wantedTable", wantedTable);
        List<String> column = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("columnName", columnName);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            Object object = rs.getObject(columnName);
            column.add(String.valueOf(object));
        });
        return column;
    }

}
