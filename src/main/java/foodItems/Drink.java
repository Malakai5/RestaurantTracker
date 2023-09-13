package foodItems;

import java.util.List;

public class Drink extends Consumable {

    private boolean isAlcoholic;
    private boolean highCaffeine;
    private List<TasteElement> tasteElements;

    public Drink(String drinkName){
        this.name = drinkName;
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

}
