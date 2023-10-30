package connections.databaseOperations;

import objects.models.ConsumableSearchForm;
import objects.models.RestaurantSearchForm;
import objects.models.SearchForm;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSearchOperations {
    private final NamedParameterJdbcTemplate queryParameter;
    public DatabaseSearchOperations(NamedParameterJdbcTemplate queryParameter) {
        this.queryParameter = queryParameter;
    }

    public List<String> getColumn(String columnName, String wantedTable){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.column");
        sqlQuery = sqlQuery.replace("wantedTable", wantedTable);
        sqlQuery = sqlQuery.replace("columnName", columnName);
        List<String> column = new ArrayList<>();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            Object object = rs.getObject(columnName);
            column.add(String.valueOf(object));
        });
        return column;
    }

    public List<String> getCities(String state){
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.cities");
        List<String> results = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("state",state);

        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            String city = rs.getString("city");
            results.add(city);
        });
        return results;
    }

    public List<Integer> processSearchRequest(SearchForm form){
        String sqlQuery = (String) BeanSearcher.getInstance()
                .lookUp("search.for." + form.getSearchFormType());
        sqlQuery = sqlQuery.replace("CONDITIONS", form.buildConditionsString());

        List<Integer> idList = new ArrayList<>();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs -> {
            idList.add(rs.getInt(form.getSearchFormType() + "_id"));
        });
        return idList;
    }
}
