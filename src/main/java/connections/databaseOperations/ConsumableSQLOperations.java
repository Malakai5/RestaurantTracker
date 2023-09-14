package connections.databaseOperations;

import foodItems.Drink;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ConsumableSQLOperations {

    private final NamedParameterJdbcTemplate queryParameter;

    public ConsumableSQLOperations(NamedParameterJdbcTemplate queryParameter) {
        this.queryParameter = queryParameter;
    }

    public void addNewDrink(Drink drink){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.drink");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_id", drink.getRestaurantID())
                .addValue("consumable_name", drink.getName())
                .addValue("consumable_type", "Drink")
                .addValue("taste_elements", drink.getTasteElementString())
                .addValue("has_dairy",drink.hasDairy())
                .addValue("has_meat", drink.hasMeat())
                .addValue("is_spicy", drink.isSpicy())
                .addValue("is_hot", drink.isHot())
                .addValue("is_favorite", drink.isFavorite())
                .addValue("is_alcoholic", drink.isAlcoholic())
                .addValue("high_caffeine", drink.isHighCaffeine());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public Drink getDrink( int drinkID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.drink");
        Drink drink = new Drink();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", drinkID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs-> {
            drink.setAlcoholic(rs.getBoolean("is_alcoholic"));
            drink.setHighCaffeine(rs.getBoolean("high_caffeine"));
            drink.setHot(rs.getBoolean("is_hot"));
            drink.setDairy(rs.getBoolean("has_dairy"));
            drink.setFavorite(rs.getBoolean("is_favorite"));
            drink.setMeat(rs.getBoolean("has_meat"));
            drink.setSpicy(rs.getBoolean("is_spicy"));
            drink.setName(rs.getString("consumable_name")); //TODO FIX THIS PLEASE
            drink.setRestaurantID(rs.getInt("restaurant_id"));
        }); //TODO Handle taste elements retrieval
        return drink;
    }

}
