package foodItems;

import objects.models.ConsumableEntryForm;
import objects.models.EntryForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Appetizer extends Consumable implements FoodItem {

    private String mainIngredient;
    private String methodOfCooking;
    private boolean shareable = false;
    private List<TasteElement> tasteElements;

    public Appetizer(String mainIngredient, String methodOfCooking, boolean shareable, List<TasteElement> tasteElements) {
        this.mainIngredient = mainIngredient;
        this.methodOfCooking = methodOfCooking;
        this.shareable = shareable;
        this.tasteElements = tasteElements;
    }

    public Appetizer(){
        this.consumableType = "Appetizer";
    }

    public List<TasteElement> getTasteElements() {
        return tasteElements;
    }

    public void setTasteElements(List<TasteElement> tasteElements) {
        this.tasteElements = tasteElements;
    }

    public boolean isShareable() {
        return shareable;
    }

    public void setShareable(boolean shareable) {
        this.shareable = shareable;
    }

    public String getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(String mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    public String getMethodOfCooking() {
        return methodOfCooking;
    }

    public void setMethodOfCooking(String methodOfCooking) {
        this.methodOfCooking = methodOfCooking;
    }

    @Override
    public String toString() {
        return name + "{" +
                "\n isShareable = " + isShareable() +
                ",\n mainIngredient = " + mainIngredient +
                ",\n methodOfCooking = " + methodOfCooking +
                ",\n favorite = " + isFavorite +
                ",\n taste = " + tasteElements +
                ",\n hasDairy = " + hasDairy +
                ",\n hasMeat = " + hasMeat +
                ",\n isSpicy = " + isSpicy +
                ",\n isHot = " + isHot +
                ",\n vegetarian = " + isVegan() +
                ",\n price = $" +  price +
                "\n}";
    }

    @Override
    public String buildSQLColumnsString() {
        return "consumable_name, consumable_type, taste_elements, " +
                "main_ingredients, method_of_cooking, restaurant_id, price, has_dairy, " +
                "has_meat, is_spicy, is_hot, is_favorite, is_shareable";
    }

    @Override
    public String buildSQLValueString() {
        return "\"" + name + "\", \"" + "Appetizer\", \"" + getTasteElementString(tasteElements) + "\", \""
                + mainIngredient + "\", \"" + methodOfCooking + "\", "  + restaurantID + ", " + price + ", " + hasDairy
                + ", " + hasMeat + ", " + isSpicy + ", " + isHot + ", " + isFavorite + ", " + shareable;
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
            setShareable(rs.getBoolean("is_shareable"));
            setMainIngredient(rs.getString("main_ingredients"));
            setMethodOfCooking(rs.getString("method_of_cooking"));
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
        setShareable(form.isShareable);
        setMainIngredient(form.mainIngredient);
        setMethodOfCooking(form.methodOfCooking);
        setPrice(form.price);
        setTasteElements(getTasteElementList(form.mainTasteElement));
        return this;
    }

}
