package connections.databaseOperations;

import foodItems.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ConsumableSQLOperations {

    private final NamedParameterJdbcTemplate queryParameter;

    public ConsumableSQLOperations(NamedParameterJdbcTemplate queryParameter) {
        this.queryParameter = queryParameter;
    }

    public void addNewConsumable(FoodItem consumable){ //TODO figure out why this isn't working
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.consumable");
        sqlQuery = sqlQuery.replace(":columns", consumable.buildSQLColumnsString());
        sqlQuery = sqlQuery.replace(":values", consumable.buildSQLValueString());
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public FoodItem getConsumable(int consumableID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.consumable");
        List<FoodItem> tempList = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", consumableID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs -> {
            String conType = rs.getString("consumable_type");
            FoodItem temp = initializeFoodType(conType);
            temp.parseResultSet(rs);
            tempList.add(temp);

        });
        return tempList.get(0);
    }

    private FoodItem initializeFoodType(String consumableType){
        return switch (consumableType) {
            case ("Entree") -> new Entree();
            case ("Appetizer") -> new Appetizer();
            case ("Dessert") -> new Dessert();
            case ("Drink") -> new Drink();
            default -> null;
        };
    }


}
