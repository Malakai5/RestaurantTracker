package foodItems;

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

    public boolean hasDairy() {
        return hasDairy;
    }

    public void setDairy(boolean hasDairy) {
        this.hasDairy = hasDairy;
    }

    public boolean hasMeat() {
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

    public boolean isVegan(){
        return (!hasMeat && !hasDairy);
    }


}
