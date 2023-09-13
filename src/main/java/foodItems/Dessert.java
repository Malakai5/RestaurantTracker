package foodItems;

import java.util.ArrayList;
import java.util.List;

public class Dessert extends Consumable {

    private List<String> mainIngredients = new ArrayList<>();

    public Dessert(String dishName) {
        this.name = dishName;
    }

    public List<String> getMainIngredients() {
        return mainIngredients;
    }

    public void setMainIngredients(List<String> mainIngredients) {
        this.mainIngredients = mainIngredients;
    }

    @Override
    public String toString() {
        return name + "{" +
                "\n favorite = " + isFavorite +
                ",\n mainIngredients = " + mainIngredients +
                ",\n hasDairy = " + hasDairy +
                ",\n hasMeat = " + hasMeat +
                ",\n isSpicy = " + isSpicy +
                ",\n isHot = " + isHot +
                ",\n vegetarian = " + isVegan() +
                "\n}";
    }

}
