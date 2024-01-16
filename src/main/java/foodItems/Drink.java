package foodItems;

import objects.models.ConsumableEntryForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Drink extends Consumable implements FoodItem {

    private boolean isAlcoholic = false;
    private boolean highCaffeine = false;
    private List<TasteElement> tasteElements;

    public Drink(){}

    public Drink(boolean isAlcoholic, boolean highCaffeine) {
        this.isAlcoholic = isAlcoholic;
        this.highCaffeine = highCaffeine;
    }

    public List<TasteElement> getTasteElements() {
        return tasteElements;
    }

    public void setTasteElements(List<TasteElement> tasteElements) {
        this.tasteElements = tasteElements;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    public boolean isHighCaffeine() {
        return highCaffeine;
    }

    public void setHighCaffeine(boolean highCaffeine) {
        this.highCaffeine = highCaffeine;
    }


    @Override
    public String toString() {
        return name + "{" +
                "\n favorite = " + isFavorite +
                ",\n isAlcoholic = " + isAlcoholic +
                ",\n highCaffeine = " + highCaffeine +
                ",\n tasteElements = " + tasteElements +
                ",\n hasDairy = " + hasDairy +
                ",\n hasMeat = " + hasMeat +
                ",\n isSpicy = " + isSpicy +
                ",\n isHot = " + isHot +
                ",\n vegetarian = " + isVegan() +
                "\n}";
    }

    @Override
    public String buildSQLColumnsString() {
        return "consumable_name, consumable_type, taste_elements, " +
                "restaurant_id, price, has_dairy, " +
                "is_spicy, is_hot, is_favorite, is_alcoholic, high_caffeine";
    }

    @Override
    public String buildSQLValueString() {
        return "\"" + name + "\", \""  + "Drink\", \"" + getTasteElementString(tasteElements)
                + "\", " + restaurantID + ", " + price + ", " + hasDairy + ", " + isSpicy + ", " + isHot
                + ", " + isFavorite + ", " + isAlcoholic + ", " + highCaffeine;
    }

    @Override
    public void parseResultSet(ResultSet rs) {
        try {
            setAlcoholic(rs.getBoolean("is_alcoholic"));
            setConsumableType(rs.getString("consumable_type"));
            setHighCaffeine(rs.getBoolean("high_caffeine"));
            setHot(rs.getBoolean("is_hot"));
            setDairy(rs.getBoolean("has_dairy"));
            setFavorite(rs.getBoolean("is_favorite"));
            setMeat(rs.getBoolean("has_meat"));
            setSpicy(rs.getBoolean("is_spicy"));
            setName(rs.getString("consumable_name"));
            setRestaurantID(rs.getInt("restaurant_id"));
            setPrice(rs.getDouble("price"));
            setTasteElements(getTasteElementList(rs.getString("taste_elements")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public FoodItem parseEntryForm(ConsumableEntryForm form) {
        setHot(form.isHot);
        setConsumableType(consumableType);
        setDairy(hasDairy);
        setFavorite(isFavorite);
        setMeat(form.hasMeat);
        setSpicy(form.isSpicy);
        setName(form.consumableName);
        setAlcoholic(form.isAlcoholic);
        setHighCaffeine(form.hasHighCaffeine);
        setPrice(form.price);
        setTasteElements(getTasteElementList(form.mainTasteElement));
        return this;
    }
}
