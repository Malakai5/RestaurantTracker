package foodItems;

import objects.models.ConsumableEntryForm;
import objects.models.EntryForm;

import java.sql.ResultSet;

public interface FoodItem {
    String buildSQLColumnsString();
    String buildSQLValueString();
    void parseResultSet(ResultSet rs);
    FoodItem parseEntryForm(ConsumableEntryForm form);

}
