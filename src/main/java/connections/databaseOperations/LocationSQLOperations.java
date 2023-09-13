package connections.databaseOperations;

import objects.Location;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class LocationSQLOperations {

    private final NamedParameterJdbcTemplate queryParameter;

    public LocationSQLOperations(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.queryParameter = namedParameterJdbcTemplate;
    }

    public void addNewLocation(Location location)
    {
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("add.location");
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("state", location.getState())
                .addValue("city", location.getCity())
                .addValue("zipcode", location.getZipCode())
                .addValue("street_name", location.getStreetName())
                .addValue("address_number", location.getAddressNumber())
                .addValue("unit_number", location.getUnitNumber());
        queryParameter.update(sqlQuery, mapSqlParameterSource);
    }

    public Location getLocation(int locationID)
    {
        String sqlQuery = (String) BeanSearcher.getInstance().lookUp("select.location");
        Location location = new Location();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", locationID);
        queryParameter.query(sqlQuery, mapSqlParameterSource, rs ->{
            location.setState(rs.getString("state"));
            location.setCity(rs.getString("city"));
            location.setZipCode(rs.getInt("zipcode"));
            location.setStreetName(rs.getString("street_name"));
            location.setAddressNumber(rs.getInt("address_number"));
            location.setUnitNumber(rs.getInt("unit_number"));
        });

        return location;
    }
}
