package objects.models;

import foodItems.*;

public class ConsumableEntryForm {
    public String consumableName;
    public String consumableType;
    public String restaurantName;
    public String mainIngredient;
    public String methodOfCooking;
    public double price;
    public boolean isAlcoholic;
    public boolean hasHighCaffeine;
    public boolean hasMeat;
    public boolean isSpicy;
    public boolean isShareable;
    public boolean hasDairy;
    public String timeOfMeal = "";
    public boolean favorite;
    public String mainTasteElement;
    public boolean isHot;

    public FoodItem initializeFoodType(){
        return switch (consumableType) {
            case ("Entree") -> new Entree();
            case ("Appetizer") -> new Appetizer();
            case ("Dessert") -> new Dessert();
            case ("Drink") -> new Drink();
            default -> null;
        };
    }

}
