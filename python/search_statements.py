import driver


def search_for_consumable(conditions):
    sql_statement = "SELECT consumable_id FROM consumable_table WHERE " + conditions
    output = driver.execute_select_statement(sql_statement, False)
    formatted_output = []
    for row in output:
        formatted_output.append(row[0])
    return formatted_output


def search_for_restaurant(conditions):
    sql_statement = "SELECT restaurant_id FROM restaurant_table INNER JOIN location_table ON restaurant_table.location_id = location_table.location_id WHERE " + conditions
    output = driver.execute_select_statement(sql_statement, False)
    formatted_output = []
    for row in output:
        formatted_output.append(row[0])
    return formatted_output

