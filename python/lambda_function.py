import object_to_json
import search_statements
import select_statements


def lambda_handler(event, context):
    if event['object_type'] == 'consumable':
        consumable_ids = search_statements.search_for_consumable(object_to_json.build_consumable_condition_string(event['item']))
        consumable_list = []
        for consumable_id in consumable_ids:
            consumable_list.append(select_statements.select_consumable(consumable_id))
        return consumable_list
    elif event['object_type'] == 'restaurant':
        restaurant_ids = search_statements.search_for_restaurant(object_to_json.build_restaurant_condition_string(event['item']))
        restaurant_list = []
        for restaurant_id in restaurant_ids:
            restaurant_list.append(select_statements.select_restaurant(restaurant_id))
        return restaurant_list


