package foodItems;

import java.util.ArrayList;
import java.util.List;

public class Consumable {

    public enum TasteElement {
        SWEET,
        SOUR,
        SALTY,
        UMAMI,
        BITTER,
        SAVORY
    }

    protected String name;

    protected int restaurantID;

    protected double price;

    protected String consumableType;

    protected boolean isFavorite = false;

    protected boolean hasDairy = false;

    protected boolean hasMeat = false;
    protected boolean isSpicy = false;
    protected boolean isHot = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isHasDairy() {
        return hasDairy;
    }

    public void setDairy(boolean hasDairy) {
        this.hasDairy = hasDairy;
    }

    public boolean isHasMeat() {
        return hasMeat;
    }

    public void setMeat(boolean hasMeat) {
        this.hasMeat = hasMeat;
    }

    public boolean isSpicy() {
        return isSpicy;
    }

    public void setSpicy(boolean spicy) {
        isSpicy = spicy;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isVegan() {
        return (!hasMeat && !hasDairy);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getConsumableType() {
        return consumableType;
    }

    public void setConsumableType(String consumableType) {
        this.consumableType = consumableType;
    }

    public String getTasteElementString(List<TasteElement> tasteElements) {
        StringBuilder sb = new StringBuilder();
        for (TasteElement tasteElement : tasteElements) {
            sb.append(tasteElement.toString()).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public List<TasteElement> getTasteElementList(String tasteElementString) {
        String[] splitString = tasteElementString.split(",");

        List<TasteElement> list = new ArrayList<>();
        for (String temp : splitString) {
            list.add(TasteElement.valueOf(temp));
        }
        return list;
    }

}
