import driver
import object_to_json


def get_restaurant_format(restaurant, location):
    restaurant_output = object_to_json.restaurant_to_json(restaurant)
    location_output = object_to_json.location_to_json(location)

    json_object = {"restaurant": restaurant_output,
                   "location": location_output}

    return json_object


def select_consumable(item_id):
    sql_statement = "SELECT * FROM consumable_table WHERE consumable_id = " + str(item_id)
    output = driver.execute_select_statement(sql_statement, True)
    consumable_output = object_to_json.consumable_to_json(output)
    sql_statement = "SELECT * FROM restaurant_table WHERE restaurant_id = " + str(output[2])
    output = driver.execute_select_statement(sql_statement, True)
    restaurant_output = object_to_json.restaurant_to_json(output)

    json_object = {"consumable": consumable_output,
                   "restaurant": restaurant_output}

    return json_object


def select_restaurant(item_id):
    sql_statement = "SELECT * FROM restaurant_table WHERE restaurant_id = " + str(item_id)
    restaurant_output = driver.execute_select_statement(sql_statement, True)
    sql_statement = "SELECT * FROM location_table WHERE location_id = " + str(restaurant_output[3])
    location_output = driver.execute_select_statement(sql_statement, True)

    return get_restaurant_format(restaurant_output, location_output)


def select_location(item_id):
    sql_statement = "SELECT * FROM location_table WHERE location_id = " + str(item_id)
    output = driver.execute_select_statement(sql_statement, True)

    return object_to_json.location_to_json(output)


def select_column(column_name, table_name):
    sql_statement = ("SELECT " + column_name + " FROM " + table_name + "_table")
    output = driver.execute_select_statement(sql_statement, False)
    formatted_output = []
    for row in output:
        formatted_output.append(row[0])
    return formatted_output


def select_cities(state):
    sql_statement = "SELECT city from location_table where state= \"" + state + "\""
    output = driver.execute_select_statement(sql_statement, False)
    formatted_output = []
    for row in output:
        formatted_output.append(row[0])
    return formatted_output


def select_similar_restaurant_list(restaurant_name):
    sql_statement = "SELECT * FROM restaurant_table WHERE restaurant_name LIKE '%" + restaurant_name + "%'"
    output = driver.execute_select_statement(sql_statement, False)
    restaurant_list = []
    for row in output:
        restaurant_list.append(object_to_json.restaurant_to_json(row))
    return restaurant_list


def select_restaurant_by_name(restaurant_name):
    sql_statement = "SELECT * FROM restaurant_table WHERE restaurant_name = '" + restaurant_name + "'"
    restaurant_output = driver.execute_select_statement(sql_statement, True)
    sql_statement = "SELECT * FROM location_table WHERE location_id = " + str(restaurant_output[3])
    location_output = driver.execute_select_statement(sql_statement, True)

    return get_restaurant_format(restaurant_output, location_output)


def select_highest_id(table_name):
    sql_statement = (
            "SELECT " + table_name + "_id FROM " + table_name + "_table WHERE " + table_name + ("_id = (SELECT "
                                                                                                "max(") +
            table_name + "_id) FROM " + table_name + "_table)")
    output = driver.execute_select_statement(sql_statement, True)
    return output[0]

