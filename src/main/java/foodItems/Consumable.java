package foodItems;

public class Consumable {

    enum TasteElement {
        SWEET,
        SOUR,
        SALTY,
        UMAMI,
        BITTER,
        SAVORY
        }

    protected String name;
    protected boolean isFavorite;
    protected boolean hasDairy;
    protected boolean hasMeat;
    protected boolean isSpicy;
    protected boolean isHot;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
