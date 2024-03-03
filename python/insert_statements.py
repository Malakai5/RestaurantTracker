import driver
import select_statements


def is_float(string):
    try:
        float(string)
        return True
    except ValueError:
        return False


def build_insert_string(object):
    values = ""

    for key in object:
        if type(object[key]) is int or type(object[key]) is float:
            values += (" " + str(object[key]) + ",")
        else:
            values += (" \"" + object[key] + "\",")

    values = values[:-1]
    if values[len(values) - 1] == ',':
        values = values[:-1]
    return values


def insert_new_object(sql_statement, object):
    values = build_insert_string(object)
    driver.execute_insert_statement(sql_statement + values + ")")
    highest_id = select_statements.select_highest_id("location")
    object_id = {"id": highest_id}
    return object_id


if __name__ == "__main__":
    location_sql = "INSERT INTO location_table(state, city, zipcode, street_name, address_number, unit_number) VALUES ("
    restaurant_sql = "INSERT INTO restaurant_table(restaurant_name, food_type, location_id, price_range, is_favorite) VALUES ("
    entree_sql = ("INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements,"
                  " main_ingredients, method_of_cooking, meal_time,"
                  " number_of_sides, price, has_meat, is_spicy,"
                  " is_hot, is_favorite, has_dairy) VALUES (")

    location = {
        "state": "FL",
        "city": "Tampa",
        "zipcode": 33612,
        "street_name": "E Fowler Ave",
        "address_number": 1524,
        "unit_number": ""
    }

    restaurant = {
        "restaurant_name": "",
        "food_type": "Halal",
        "location_id": "2",
        "price_range": "$",
        "is_favorite": "1"
    }

    entree = {
        "consumable_name": "Baked Potato",
        "restaurant_id": "2",
        "consumable_type": "Entree",
        "taste_elements": "SAVORY",
        "main_ingredients": "Baked Potato, Lamb, Chicken",
        "method_of_cooking": "Baked",
        "meal_time": "Dinner",
        "number_of_sides": "1",
        "price": "12.99",
        "has_meat": "1",
        "is_spicy": "0",
        "is_hot": "1",
        "is_favorite": "1",
        "has_dairy": "1"
    }

    app_sql = ("INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements, " +
               "main_ingredients, method_of_cooking, price, has_dairy, " +
               "has_meat, is_spicy, is_hot, is_favorite, is_shareable) VALUES (")

    app = {
        "consumable_name": "Baba Ghanoush",
        "restaurant_id": "2",
        "consumable_type": "Appetizer",
        "taste_elements": "SAVORY",
        "main_ingredients": "Eggplant, Tahini",
        "method_of_cooking": "Pureed",
        "price": "6.99",
        "has_dairy": "0",
        "has_meat": "0",
        "is_spicy": "0",
        "is_hot": "1",
        "is_favorite": "0",
        "is_shareable": "1"
    }

    dessert = {
        "consumable_name": "Guava Cheesecake",
        "restaurant_id": "2",
        "consumable_type": "Dessert",
        "taste_elements": "SWEET",
        "main_ingredients": "Guava, Cheesecake",
        "price": "6.99",
        "is_hot": "0",
        "is_favorite": "0",
        "has_dairy": "1"
    }

    dessert_sql = ("INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements, "
                   "main_ingredients, price, is_hot, is_favorite, has_dairy) VALUES (")

    # insert_new_object(restaurant_sql, restaurant)
    insert_new_object(location_sql, location)
