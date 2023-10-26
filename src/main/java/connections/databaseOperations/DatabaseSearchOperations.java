package connections.databaseOperations;

import objects.models.ConsumableSearchForm;
import objects.models.RestaurantSearchForm;
import objects.models.SearchForm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
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

    public List<Integer> searchForRestaurants(RestaurantSearchForm form){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("search.for.restaurants");
        sqlQuery = sqlQuery.replace("CONDITIONS",makeConditionStatement(form));

        List<Integer> idList = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs -> {
            idList.add(rs.getInt("restaurant_id"));
        });
        return idList;
    }

    public List<Integer> searchForConsumables(ConsumableSearchForm form){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("search.for.consumables");
        sqlQuery = sqlQuery.replace("CONDITIONS",makeConditionStatement(form));
        System.out.println(sqlQuery);
        List<Integer> idList = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs -> {
            idList.add(rs.getInt("consumable_id"));
        });
        return idList;
    }



    private String makeConditionStatement(RestaurantSearchForm form){ //TODO MAKE THIS A SEARCH FORM FUNCTION
        StringBuilder sb = new StringBuilder();

        if (!form.restaurantName.isEmpty()) {
            sb.append("restaurant_name LIKE \"").append(form.restaurantName).append("\"");
        }
        else{
            sb.append("restaurant_name LIKE \"%\"");
        }

        if (!form.city.isEmpty()) {
            sb.append(" AND city=\"").append(form.city).append("\"");
        }
        if (!form.state.isEmpty()) {
            sb.append(" AND state=\"").append(form.state).append("\"");
        }
        if (!form.priceRange.isEmpty()) {
            sb.append(" AND price_range=\"").append(form.priceRange).append("\"");
        }
        if (!form.foodType.isEmpty()) {
            sb.append(" AND food_type=\"").append(form.foodType).append("\"");
        }
        if (form.favorite) {
            sb.append(" AND is_favorite=\"").append("1").append("\"");
        }
        return sb.toString();
    }
    private String makeConditionStatement(ConsumableSearchForm form){
        StringBuilder sb = new StringBuilder();

        if (!form.consumableName.isEmpty()) {
            sb.append("consumable_name LIKE \"").append(form.consumableName).append("\"");
        }
        else{
            sb.append("consumable_name LIKE \"%\"");
        }

        if (!form.consumableType.isEmpty()){
            sb.append(" AND consumable_type=\"").append(form.consumableType).append("\"");
            if (!form.timeOfMeal.isEmpty()){
                sb.append(" AND meal_time=\"").append(form.timeOfMeal).append("\"");
            }
            if (form.isAlcoholic){
                sb.append(" AND is_alcoholic=\"").append(true).append("\"");
            }
            if (form.hasHighCaffeine){
                sb.append(" AND high_caffeine=\"").append(true).append("\"");
            }
        }

        if (form.hasMeat){
            sb.append(" AND has_meat=\"").append(true).append("\"");
        }
        if (form.hasDairy){
            sb.append(" AND has_dairy=\"").append(true).append("\"");
        }
        if (form.favorite) {
            sb.append(" AND is_favorite=\"").append(true).append("\"");
        }
        if (!form.mainTasteElement.isEmpty()){
            sb.append(" AND taste_elements=\"").append(form.mainTasteElement).append("\"");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
