package foodItems;

import java.util.ArrayList;
import java.util.List;

public class Entree extends Consumable {

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
}
