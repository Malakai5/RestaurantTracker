package foodItems;

import java.sql.ResultSet;

public interface FoodItem {
    String buildSQLColumnsString();
    String buildSQLValueString();
    void parseResultSet(ResultSet rs);

}
