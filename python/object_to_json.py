import json

import select_statements


def split_query_results(query_results):
    query_results = query_results.replace("(", "")
    query_results = query_results.replace(")", "")
    results = query_results.split(",")
    return results


def consumable_to_json(object_keys):
    consumable_fields_list = ["consumable_id",
                              "consumable_name",
                              "restaurant_id",
                              "consumable_type",
                              "taste_elements",
                              "has_dairy",
                              "has_meat",
                              "is_spicy",
                              "is_hot",
                              "is_favorite",
                              "meal_time",
                              "main_ingredients",
                              "method_of_cooking",
                              "is_shareable",
                              "is_alcoholic",
                              "high_caffeine",
                              "number_of_sides",
                              "price"]
    consumable_object = {}
    for index in range(len(consumable_fields_list)):
        consumable_object[consumable_fields_list[index]] = object_keys[index]

    consumable_json = json.dumps(consumable_object)
    return consumable_json


def restaurant_to_json(object_keys):
    restaurant_fields_list = ["restaurant_id",
                              "restaurant_name",
                              "food_type",
                              "location_id",
                              "price_range",
                              "is_favorite"]
    restaurant_object = {}
    for index in range(len(restaurant_fields_list)):
        restaurant_object[restaurant_fields_list[index]] = object_keys[index]
    restaurant_json = json.dumps(restaurant_object)
    return restaurant_json


def location_to_json(object_keys):
    location_fields_list = ["location_id",
                            "state",
                            "city",
                            "zipcode",
                            "street_name",
                            "address_number",
                            "unit_number"]
    location_object = {}
    for index in range(len(location_fields_list)):
        location_object[location_fields_list[index]] = object_keys[index]
    location_json = json.dumps(location_object)
    return location_json


def build_consumable_condition_string(object_json):
    condition_string = ""
    if len(object_json["consumable_name"]) != 0:
        condition_string += "consumable_name LIKE \"" + object_json["consumable_name"] + "\""
    else:
        condition_string += "consumable_name LIKE \"%\""

    if len(object_json["consumable_type"]) != 0:
        condition_string += " AND consumable_type=\"" + object_json["consumable_type"] + "\""
        if len(object_json["meal_time"]) != 0:
            condition_string += " AND meal_time=\"" + object_json["meal_time"] + "\""
        if object_json["is_alcoholic"]:
            condition_string += " AND is_alcoholic=True"
        if object_json["high_caffeine"]:
            condition_string += " AND high_caffeine=True"

    if object_json["has_meat"]:
        condition_string += " AND has_meat=True"
    if object_json["has_dairy"]:
        condition_string += " AND has_dairy=True"
    if object_json["is_favorite"]:
        condition_string += " AND is_favorite=True"

    if len(object_json["taste_elements"]) != 0:
        condition_string += " AND taste_elements=\"" + object_json["taste_elements"] + "\""

    return condition_string


def build_restaurant_condition_string(object_json):
    condition_string = ""
    if len(object_json["restaurant_name"]) != 0:
        condition_string += "restaurant_name LIKE \"" + object_json["restaurant_name"] + "\""
    else:
        condition_string += "restaurant_name LIKE \"%\""

    if len(object_json["food_type"]) != 0:
        condition_string += " AND food_type= \"" + object_json["food_type"] + "\""
    if len(object_json["city"]) != 0:
        condition_string += " AND city= \"" + object_json["city"] + "\""
    if len(object_json["state"]) != 0:
        condition_string += " AND state= \"" + object_json["state"] + "\""
    if len(object_json["price_range"]) != 0:
        condition_string += " AND price_range= \"" + object_json["price_range"] + "\""
    if object_json["is_favorite"]:
        condition_string += "AND is_favorite= True"

    return condition_string
