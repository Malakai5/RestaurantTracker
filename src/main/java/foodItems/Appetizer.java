package foodItems;

import java.util.List;

public class Appetizer extends Consumable{

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

    public Appetizer(){}

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
                ",\n price = $" + price +
                "\n}";
    }
}
