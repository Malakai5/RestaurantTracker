package connections.databaseOperations;

import objects.models.RestaurantSearchForm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DatabaseSearchOperations {
    private final NamedParameterJdbcTemplate queryParameter;
    public DatabaseSearchOperations(NamedParameterJdbcTemplate queryParameter) {
        this.queryParameter = queryParameter;
    }

    public List<String> getColumn(String columnName, String wantedTable){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.column");
        sqlQuery = sqlQuery.replace("wantedTable", wantedTable);
        sqlQuery = sqlQuery.replace("columnName", columnName);
        List<String> column = new ArrayList<>();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            Object object = rs.getObject(columnName);
            column.add(String.valueOf(object));
        });
        return column;
    }

    public List<String> getCities(String state){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.cities");
        List<String> results = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("state",state);

        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            String city = rs.getString("city");
            results.add(city);
        });
        return results;
    }

    public List<Integer> searchForRestaurants(RestaurantSearchForm form)
    {
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("search.for.restaurants");
        sqlQuery = sqlQuery.replace("CONDITIONS",makeConditionStatement(form));

        System.out.println(sqlQuery);
        List<Integer> idList = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs -> {
            idList.add(rs.getInt("restaurant_id"));
        });
        return idList;
    }

    private String makeConditionStatement(RestaurantSearchForm form){
        StringBuilder sb = new StringBuilder("restaurant_name=\"" + form.restaurantName + "\"");

//        if (!form.city.isEmpty()) {
//            sb.append("AND city=").append(form.city).append("\"");
//        }
//        else if (!form.state.isEmpty()) {
//            sb.append("AND state=\"").append(form.state).append("\"");
//        }
        if (!form.priceRange.isEmpty()) {
            sb.append(" AND price_range=\"").append(form.priceRange).append("\"");
        }
        if (!form.foodType.isEmpty()) {
            sb.append(" AND food_type=\"").append(form.foodType).append("\"");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
