package foodItems;

import java.util.ArrayList;
import java.util.List;

public class Dessert extends Consumable {

    private List<String> mainIngredients = new ArrayList<>();

    public Dessert() {}

    public Dessert(List<String> mainIngredients) {
        this.mainIngredients = mainIngredients;
    }

    public List<String> getMainIngredients() {
        return mainIngredients;
    }

    public void setMainIngredients(List<String> mainIngredients) {
        this.mainIngredients = mainIngredients;
    }

}
