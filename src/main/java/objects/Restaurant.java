package objects;

public class Restaurant {
    private String name;
    private Location location;
    private String foodStyle;
    private String priceRange;
    private int locationID;

    private boolean favorite;

    public Restaurant() {
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", location=" + location +
                ", foodStyle='" + foodStyle + '\'' +
                ", priceRange='" + priceRange + '\'' +
                ", locationID=" + locationID +
                '}';
    }

    public Restaurant(String name, Location location, String foodStyle, String priceRange, int locationID) {
        this.name = name;
        this.location = location;
        this.foodStyle = foodStyle;
        this.priceRange = priceRange;
        this.locationID = locationID;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFoodStyle() {
        return foodStyle;
    }
    public void setFoodStyle(String foodStyle) {
        this.foodStyle = foodStyle;
    }
    public int getLocationID() {
        return locationID;
    }
    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public int isFavorite() {
        if (favorite) {
            return 1;
        }
        else {
            return 0;
        }
    }
    public void setFavorite(int favorite) {
        if (favorite == 0) {
            this.favorite = false;
        }
        else {
            this.favorite = true;
        }
    }

}
