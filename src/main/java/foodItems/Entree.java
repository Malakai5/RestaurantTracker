package foodItems;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Entree extends Consumable implements FoodItem {

    private String mealTime;
    private int numberOfSides;
    private String mainIngredient;
    private String methodOfCooking;
    private List<TasteElement> tasteElements = new ArrayList<>();

    public Entree(String mealTime, int numberOfSides, String mainIngredient, String methodOfCooking, List<TasteElement> tasteElements) {
        this.mealTime = mealTime;
        this.numberOfSides = numberOfSides;
        this.mainIngredient = mainIngredient;
        this.methodOfCooking = methodOfCooking;
        this.tasteElements = tasteElements;
    }

    public Entree(){}

    public List<TasteElement> getTasteElements() {
        return tasteElements;
    }

    public void setTasteElements(List<TasteElement> tasteElements) {
        this.tasteElements = tasteElements;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void setNumberOfSides(int numberOfSides) {
        this.numberOfSides = numberOfSides;
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
                "\n numberOfSides = " + numberOfSides +
                ",\n mealTime = " + mealTime +
                ",\n mainIngredient = " + mainIngredient +
                ",\n methodOfCooking = " + methodOfCooking +
                ",\n favorite = " + isFavorite +
                ",\n taste = " + tasteElements +
                ",\n hasDairy = " + hasDairy +
                ",\n hasMeat = " + hasMeat +
                ",\n isSpicy = " + isSpicy +
                ",\n isHot = " + isHot +
                ",\n vegetarian = " + isVegan() +
                "\n}";
    }

    @Override
    public String buildSQLColumnsString() {
        return "consumable_name, restaurant_id, consumable_type, taste_elements, " +
                "main_ingredients, method_of_cooking, meal_time, number_of_sides, price, " +
                "has_meat, is_spicy, is_hot, is_favorite, has_dairy";
    }

    @Override
    public String buildSQLValueString() {
        return " \"" + name + "\", " + restaurantID + ", \"Entree\", \"" + getTasteElementString(tasteElements) + "\", \""
                + mainIngredient + "\", \"" + methodOfCooking + "\", \"" + mealTime
                + "\", " + numberOfSides + ", " + price + ", " + hasMeat
                + ", " + isSpicy + ", " + isHot + ", " + isFavorite
                + ", " + hasDairy;
    }

    @Override
    public void parseResultSet(ResultSet rs) {
        try{
            setHot(rs.getBoolean("is_hot"));
            setConsumableType(rs.getString("consumable_type"));
            setDairy(rs.getBoolean("has_dairy"));
            setFavorite(rs.getBoolean("is_favorite"));
            setMeat(rs.getBoolean("has_meat"));
            setSpicy(rs.getBoolean("is_spicy"));
            setName(rs.getString("consumable_name"));
            setRestaurantID(rs.getInt("restaurant_id"));
            setMealTime(rs.getString("meal_time"));
            setMainIngredient(rs.getString("main_ingredients"));
            setMethodOfCooking(rs.getString("method_of_cooking"));
            setNumberOfSides(rs.getInt("number_of_sides"));
            setPrice(rs.getDouble("price"));
            setTasteElements(getTasteElementList(rs.getString("taste_elements")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
