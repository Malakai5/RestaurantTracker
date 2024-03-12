function splitAddress(address){
    let addressSplit = []
    addressSplit[0] = address.substring(0, address.indexOf(" "))
    addressSplit[1] = address.substring(address.indexOf(" ") + 1)
    return addressSplit
}

function newLocation(){
    let sqlStatement = "INSERT INTO location_table(state, city, zipcode, street_name, address_number, unit_number) VALUES ("
    let data = new FormData(document.getElementById("restaurantEntry"));
    let addressArray = splitAddress(data.get("address"));

    let location = {
        state: data.get("state"),
        city: data.get("city"),
        zipcode: data.get("zipcode"),
        street_name: addressArray[1],
        address_number: addressArray[0],
        unit_number: data.get("unit_number")
    }

    let raw = JSON.stringify({
        "sqlStatement": sqlStatement,
        "object": location});

    let requestOptions = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: raw,
        redirect: 'follow'
    }
    return fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/Insert", requestOptions)
        .then((response) => response.text())
        .then((text) => JSON.parse(text))
        .then(json => {
            console.log(json.statusCode)
            console.log(json.body)
            return json.body
        })}

function newRestaurant(){
    let sqlStatement = "INSERT INTO restaurant_table(restaurant_name, food_type, price_range, is_favorite, location_id) VALUES (";
    let data = new FormData(document.getElementById("restaurantEntry"));
    newLocation().then(json => {
            let restaurant = {
                restaurant_name : data.get("restaurant_name"),
                food_type : data.get("food_type"),
                price_range : data.get("price_range"),
                is_favorite : data.get("is_favorite"),
                location : json["id"]
            }

            let raw = JSON.stringify({
                "sqlStatement": sqlStatement,
                "object": restaurant});

            let requestOptions = {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: raw,
                redirect: 'follow'
            }
            console.log(JSON.parse(raw))

            fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/Insert", requestOptions)
                .then(response => response.text())
                .then(response => JSON.parse(response))
                .then(json => {
                    if (json.statusCode === '200'){
                        document.getElementById("restaurantEntry").reset()
                    }
                })
        })
}

function formatEntree(data){
   return {
        consumable_name : data.get("consumable_name"),
        restaurant_id : data.get("restaurant_id"),
        consumable_type : data.get("consumable_type"),
        taste_elements : data.get("taste_elements"),
        main_ingredients : data.get("main_ingredients"),
        method_of_cooking : data.get("method_of_cooking"),
        meal_time : data.get("meal_time"),
        number_of_sides : data.get("number_of_sides"),
        price : data.get("price"),
        has_meat : data.get("has_meat"),
        is_spicy : data.get("is_spicy"),
        is_hot : data.get("is_hot"),
        is_favorite : data.get("is_favorite"),
        has_dairy : data.get("has_dairy")
    }
}
function formatAppetizer(data){
    return {
        consumable_name: data.get("consumable_name"),
        restaurant_id: data.get("restaurant_id"),
        consumable_type: data.get("consumable_type"),
        taste_elements: data.get("taste_elements"),
        main_ingredients: data.get("main_ingredients"),
        method_of_cooking: data.get("method_of_cooking"),
        price : data.get("price"),
        has_dairy : data.get("has_dairy"),
        has_meat : data.get("has_meat"),
        is_spicy : data.get("is_spicy"),
        is_hot : data.get("is_hot"),
        is_favorite : data.get("is_favorite"),
        is_shareable : data.get("is_shareable")
    }
}
function formatDessert(data){
    return {
        consumable_name: data.get("consumable_name"),
        restaurant_id: data.get("restaurant_id"),
        consumable_type: data.get("consumable_type"),
        taste_elements: "SWEET",
        main_ingredients: data.get("main_ingredients"),
        price : data.get("price"),
        is_hot : data.get("is_hot"),
        is_favorite : data.get("is_favorite"),
        has_dairy : data.get("has_dairy")
    }
}
function formatDrink(data){
    return {
        consumable_name: data.get("consumable_name"),
        restaurant_id: data.get("restaurant_id"),
        consumable_type: data.get("consumable_type"),
        taste_elements: data.get("taste_elements"),
        is_hot : data.get("is_hot"),
        is_favorite : data.get("is_favorite"),
        has_dairy : data.get("has_dairy"),
        is_spicy : data.get("is_spicy"),
        is_alcoholic : data.get("is_alcoholic"),
        high_caffeine : data.get("high_caffeine")
    }
}
function populateMap(){
    let restaurantName = document.getElementById("restaurantName").value

    let raw = JSON.stringify({
        "object_type":"restaurant_by_name",
        "key": restaurantName});
    let requestOptions = {
        method: 'POST',
        body: raw,
        redirect: 'follow'
    };


    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(json => json.body)
        .then(body => {
            let restaurant = JSON.parse(body.restaurant)
            let location = JSON.parse(body.location)
            document.getElementById("address-read").value = location.address_number + " " + location.street_name
            if (location.address_number !== 0){
                document.getElementById("apt-read").value = location.unit_number
            }
            document.getElementById("city-read").value = location.city
            document.getElementById("state-read").value = location.state
            document.getElementById("zipcode-read").value = location.zipcode
            document.getElementById("restaurantId").value = restaurant.restaurant_id
        })
}

function getConsumableObject(data) {
    let sqlStatement
    let object

    if (data.get("consumable_type") === "Entree") {
        sqlStatement = "INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, " +
            "taste_elements, main_ingredients, method_of_cooking, meal_time, " +
            "number_of_sides, price, has_meat, is_spicy, is_hot, is_favorite, has_dairy) VALUES ("
        object = formatEntree(data)
    }
    if (data.get("consumable_type") === "Appetizer") {
        sqlStatement = "INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements, " +
            "main_ingredients, method_of_cooking, price, has_dairy, " +
            "has_meat, is_spicy, is_hot, is_favorite, is_shareable) VALUES ("
        object = formatAppetizer(data)
    }
    if (data.get("consumable_type") === "Dessert") {
        sqlStatement = "INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements, " +
            "main_ingredients, price, is_hot, is_favorite, has_dairy) VALUES ("
        object = formatDessert(data)
    }
    if (data.get("consumable_type") === "Drink") {
        sqlStatement = "INSERT INTO consumable_table(consumable_name, restaurant_id, consumable_type, taste_elements, " +
            "price, is_hot, is_favorite, has_dairy, is_spicy, is_alcoholic, high_caffeine) VALUES ("
        object = formatDrink(data)
    }
    return JSON.stringify({
        sqlStatement: sqlStatement,
        object: object
    })
}

function newConsumable(){
    let data = new FormData(document.getElementById("consumableEntry"));
    let object = getConsumableObject(data)
    console.log(JSON.parse(object))

    let requestOptions = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: object,
        redirect: 'follow'
    }

    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/Insert", requestOptions)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(json => {
                if (json.statusCode === '200'){
                    document.getElementById("consumableEntry").reset()
                }
        })
    }


function clearDatalist(datalist){
    let childArray = datalist.children
    let datalistSize = childArray.length
    while(datalistSize > 0) {
        datalistSize--;
        datalist.removeChild(childArray[datalistSize]);
    }
}
async function getRestaurantOptions() {
    let selectedValue = document.getElementById("restaurantName").value;
    let dataList = document.getElementById("restaurantOptions")

    let raw = JSON.stringify({
            "object_type": "similar_restaurant",
            "key": selectedValue,
            "table": "restaurant"
        }
    );

    let requestOptions = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: raw,
        redirect: 'follow'
    }

    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(json => json.body)
        .then((body) => {
            clearDatalist(dataList)
            for (let i = 0; i < body.length; i++) {
                let currentObject = JSON.parse(body[i])
                let option = document.createElement('option');
                option.value = currentObject.restaurant_name
                dataList.appendChild(option)
            }
        })
}


let restaurantNameBox = document.getElementById("restaurantName");
restaurantNameBox.addEventListener("change", getRestaurantOptions)
restaurantNameBox.addEventListener("input", populateMap)

