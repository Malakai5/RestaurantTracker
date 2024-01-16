package connections.databaseOperations;

import objects.Restaurant;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSQLOperations {
    private final NamedParameterJdbcTemplate queryParameter;

    public RestaurantSQLOperations(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.queryParameter = namedParameterJdbcTemplate;
    }

    public int addNewRestaurant(Restaurant restaurant)
    {
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.restaurant");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_name", restaurant.getName())
                .addValue("food_type", restaurant.getFoodStyle())
                .addValue("location_id", restaurant.getLocationID())
                .addValue("price_range", restaurant.getPriceRange())
                .addValue("is_favorite", restaurant.isFavorite());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
        return 0;
    }

    public Restaurant getRestaurant(int restaurantID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.restaurant");
        Restaurant restaurant = new Restaurant();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", restaurantID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            restaurant.setName(rs.getString("restaurant_name"));
            restaurant.setFoodStyle(rs.getString("food_type"));
            restaurant.setPriceRange(rs.getString("price_range"));
            restaurant.setLocationID(rs.getInt("location_id"));
            restaurant.setFavorite(rs.getBoolean("is_favorite"));
        });
        return restaurant;
    }

    public List<Restaurant> getRestaurantList(String restaurantName){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("get.restaurant.list");
        sqlQuery = sqlQuery.replace("temp", restaurantName);
        List<Restaurant> restaurants = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            Restaurant restaurant = new Restaurant();
            restaurant.setName(rs.getString("restaurant_name"));
            restaurant.setFoodStyle(rs.getString("food_type"));
            restaurant.setPriceRange(rs.getString("price_range"));
            restaurant.setLocationID(rs.getInt("location_id"));
            restaurant.setFavorite(rs.getBoolean("is_favorite"));
            restaurants.add(restaurant);
        });
        return restaurants;
    }

}
