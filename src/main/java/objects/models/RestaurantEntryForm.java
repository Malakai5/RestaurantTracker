package objects.models;

import objects.Location;
import objects.Restaurant;

public class RestaurantEntryForm {

    public String restaurantName;
    public String state;
    public String city;
    public int zipcode;
    public String address;
    public String foodType;
    public String priceRange;
    public int unitNumber;
    public boolean favorite;

    public Location constructLocation(){
        Location location = new Location();
        location.setState(this.state);
        location.setCity(this.city);
        location.setZipCode(this.zipcode);
        int firstSpaceIndex = this.address.indexOf(" ");
        location.setAddressNumber(Integer.parseInt(this.address.substring(0, firstSpaceIndex)));
        location.setStreetName(this.address.substring(firstSpaceIndex + 1));
        if (this.unitNumber != 0){
            location.setUnitNumber(this.unitNumber);
        }
        return location;
    }

    public Restaurant constructRestaurant(int locationId){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantName);
        restaurant.setFoodStyle(foodType);
        restaurant.setPriceRange(priceRange);
        restaurant.setFavorite(favorite);
        restaurant.setLocationID(locationId);
        return restaurant;
    }
}
