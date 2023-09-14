package foodItems;

import java.util.List;

public class Appetizer extends Consumable{

    private String mainIngredient;
    private String methodOfCooking;
    private boolean shareable = false;
    private List<TasteElement> tasteElements;

    public Appetizer(String dishName){
        this.name = dishName;
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

    public String tasteElementString()
    {
        StringBuilder sb = new StringBuilder();
        for(TasteElement tasteElement : tasteElements)
        {
            sb.append(tasteElement.toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public String toString() {
        return name + "{" +
                "\n isShareable = " + isShareable() +
                ",\n mainIngredient = " + mainIngredient +
                ",\n methodOfCooking = " + methodOfCooking +
                ",\n favorite = " + name +
                ",\n taste = " + tasteElements +
                ",\n hasDairy = " + hasDairy +
                ",\n hasMeat = " + hasMeat +
                ",\n isSpicy = " + isSpicy +
                ",\n isHot = " + isHot +
                ",\n vegetarian = " + isVegan() +
                "\n}";
    }
}
