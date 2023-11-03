package foodItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dessert extends Consumable implements FoodItem {

    private List<String> mainIngredients = new ArrayList<>();

    public TasteElement getTasteElements() {
        return tasteElements;
    }

    private final TasteElement tasteElements = TasteElement.SWEET;

    public Dessert() {
        this.consumableType = "Dessert";
    }

    public Dessert(List<String> mainIngredients) {
        this.mainIngredients = mainIngredients;
    }

    public List<String> getMainIngredients() {
        return mainIngredients;
    }

    public void setMainIngredients(List<String> mainIngredients) {
        this.mainIngredients = mainIngredients;
    }

    @Override
    public String buildSQLColumnsString() {
        return "consumable_name, consumable_type, " +
                "taste_elements, main_ingredients, restaurant_id, price, " +
                "is_hot, is_favorite, has_dairy";
    }

    @Override
    public String buildSQLValueString() {
        return "\"" + name + "\", " + "\"Dessert\", \"SWEET\", \"" + mainIngredients
                + "\", " + restaurantID + ", " + price + ", " + isHot + ", " + isFavorite + ", " + hasDairy;
    }

    @Override
    public void parseResultSet(ResultSet rs) {
        try {
            setHot(rs.getBoolean("is_hot"));
            setConsumableType(rs.getString("consumable_type"));
            setDairy(rs.getBoolean("has_dairy"));
            setFavorite(rs.getBoolean("is_favorite"));
            setMeat(rs.getBoolean("has_meat"));
            setSpicy(rs.getBoolean("is_spicy"));
            setName(rs.getString("consumable_name"));
            setRestaurantID(rs.getInt("restaurant_id"));
            setPrice(rs.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
