package objects.models;

public class RestaurantSearchForm implements SearchForm{

    public String restaurantName;

    public String state;
    public String city;
    public String foodType;
    public String priceRange;
    public boolean favorite;

    @Override
    public String buildConditionsString() {
        StringBuilder sb = new StringBuilder();


        if (!restaurantName.isEmpty()) {
            sb.append("restaurant_name LIKE \"").append(restaurantName).append("\"");
        }
        else{
            sb.append("restaurant_name LIKE \"%\"");
        }

        if (!city.isEmpty()) {
            sb.append(" AND city=\"").append(city).append("\"");
        }
        if (!state.isEmpty()) {
            sb.append(" AND state=\"").append(state).append("\"");
        }
        if (!priceRange.isEmpty()) {
            sb.append(" AND price_range=\"").append(priceRange).append("\"");
        }
        if (!foodType.isEmpty()) {
            sb.append(" AND food_type=\"").append(foodType).append("\"");
        }
        if (favorite) {
            sb.append(" AND is_favorite=\"").append("1").append("\"");
        }

        return sb.toString();
    }

    @Override
    public String getSearchFormType() {
        return "restaurant";
    }
}
