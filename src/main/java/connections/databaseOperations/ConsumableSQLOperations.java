package connections.databaseOperations;

import foodItems.Appetizer;
import foodItems.Dessert;
import foodItems.Drink;
import foodItems.Entree;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ConsumableSQLOperations {

    private final NamedParameterJdbcTemplate queryParameter;

    public ConsumableSQLOperations(NamedParameterJdbcTemplate queryParameter) {
        this.queryParameter = queryParameter;
    }

    public void addNewDrink(Drink drink){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.drink");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_id", drink.getRestaurantID())
                .addValue("consumable_name", drink.getName())
                .addValue("consumable_type", "Drink")
                .addValue("taste_elements", drink.getTasteElementString(drink.getTasteElements()))
                .addValue("has_dairy",drink.hasDairy())
                .addValue("has_meat", drink.hasMeat())
                .addValue("is_spicy", drink.isSpicy())
                .addValue("is_hot", drink.isHot())
                .addValue("is_favorite", drink.isFavorite())
                .addValue("is_alcoholic", drink.isAlcoholic())
                .addValue("high_caffeine", drink.isHighCaffeine());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public Drink getDrink( int drinkID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.consumable");
        Drink drink = new Drink();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", drinkID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs-> {
            drink.setAlcoholic(rs.getBoolean("is_alcoholic"));
            drink.setHighCaffeine(rs.getBoolean("high_caffeine"));
            drink.setHot(rs.getBoolean("is_hot"));
            drink.setDairy(rs.getBoolean("has_dairy"));
            drink.setFavorite(rs.getBoolean("is_favorite"));
            drink.setMeat(rs.getBoolean("has_meat"));
            drink.setSpicy(rs.getBoolean("is_spicy"));
            drink.setName(rs.getString("consumable_name"));
            drink.setRestaurantID(rs.getInt("restaurant_id"));
            drink.setTasteElements(drink.getTasteElementList(rs.getString("taste_elements")));
        });
        return drink;
    }



    public void addNewDessert(Dessert dessert){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.dessert");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_id", dessert.getRestaurantID())
                .addValue("consumable_name", dessert.getName())
                .addValue("consumable_type", "Dessert")
                .addValue("taste_elements", ("SWEET"))
                .addValue("has_dairy",dessert.hasDairy())
                .addValue("has_meat", dessert.hasMeat())
                .addValue("is_spicy", dessert.isSpicy())
                .addValue("is_hot", dessert.isHot())
                .addValue("is_favorite", dessert.isFavorite())
                .addValue("main_ingredients", dessert.getMainIngredients().toString());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public Dessert getDessert( int dessertID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.consumable");
        Dessert dessert = new Dessert();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", dessertID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs-> {
            dessert.setHot(rs.getBoolean("is_hot"));
            dessert.setDairy(rs.getBoolean("has_dairy"));
            dessert.setFavorite(rs.getBoolean("is_favorite"));
            dessert.setMeat(rs.getBoolean("has_meat"));
            dessert.setSpicy(rs.getBoolean("is_spicy"));
            dessert.setName(rs.getString("consumable_name"));
            dessert.setRestaurantID(rs.getInt("restaurant_id"));
        });
        return dessert;
    }



    public void addNewAppetizer(Appetizer appetizer){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.appetizer");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_id", appetizer.getRestaurantID())
                .addValue("consumable_name", appetizer.getName())
                .addValue("consumable_type", "Appetizer")
                .addValue("taste_elements", (appetizer.getTasteElementString(appetizer.getTasteElements())))
                .addValue("has_dairy",appetizer.hasDairy())
                .addValue("has_meat", appetizer.hasMeat())
                .addValue("is_spicy", appetizer.isSpicy())
                .addValue("is_hot", appetizer.isHot())
                .addValue("is_favorite", appetizer.isFavorite())
                .addValue("main_ingredients", appetizer.getMainIngredient())
                .addValue("is_shareable", appetizer.isShareable())
                .addValue("method_of_cooking", appetizer.getMethodOfCooking());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public Appetizer getAppetizer( int appetizerID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.consumable");
        Appetizer appetizer = new Appetizer();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", appetizerID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs-> {
            appetizer.setHot(rs.getBoolean("is_hot"));
            appetizer.setDairy(rs.getBoolean("has_dairy"));
            appetizer.setFavorite(rs.getBoolean("is_favorite"));
            appetizer.setMeat(rs.getBoolean("has_meat"));
            appetizer.setSpicy(rs.getBoolean("is_spicy"));
            appetizer.setName(rs.getString("consumable_name"));
            appetizer.setRestaurantID(rs.getInt("restaurant_id"));
            appetizer.setShareable(rs.getBoolean("is_shareable"));
            appetizer.setMainIngredient(rs.getString("main_ingredients"));
            appetizer.setMethodOfCooking(rs.getString("method_of_cooking"));
            appetizer.setTasteElements(appetizer.getTasteElementList(rs.getString("taste_elements")));
        });
        return appetizer;
    }


    public void addNewEntree(Entree entree){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.entree");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("restaurant_id", entree.getRestaurantID())
                .addValue("consumable_name", entree.getName())
                .addValue("consumable_type", "Entree")
                .addValue("taste_elements", (entree.getTasteElementString(entree.getTasteElements())))
                .addValue("has_dairy",entree.hasDairy())
                .addValue("has_meat", entree.hasMeat())
                .addValue("is_spicy", entree.isSpicy())
                .addValue("is_hot", entree.isHot())
                .addValue("is_favorite", entree.isFavorite())
                .addValue("main_ingredients", entree.getMainIngredient())
                .addValue("meal_time", entree.getMealTime())
                .addValue("number_of_sides", entree.getNumberOfSides())
                .addValue("method_of_cooking", entree.getMethodOfCooking());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }
    public Entree getEntree(int entreeID){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.consumable");
        Entree entree = new Entree();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("consumable_id", entreeID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs-> {
            entree.setHot(rs.getBoolean("is_hot"));
            entree.setDairy(rs.getBoolean("has_dairy"));
            entree.setFavorite(rs.getBoolean("is_favorite"));
            entree.setMeat(rs.getBoolean("has_meat"));
            entree.setSpicy(rs.getBoolean("is_spicy"));
            entree.setName(rs.getString("consumable_name"));
            entree.setRestaurantID(rs.getInt("restaurant_id"));
            entree.setMealTime(rs.getString("meal_time"));
            entree.setMainIngredient(rs.getString("main_ingredients"));
            entree.setMethodOfCooking(rs.getString("method_of_cooking"));
            entree.setNumberOfSides(rs.getInt("number_of_sides"));
            entree.setTasteElements(entree.getTasteElementList(rs.getString("taste_elements")));
        });
        return entree;
    }

}
