package foodItems;

import java.util.List;

public class Drink extends Consumable {

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
    public String getTasteElementString()
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
