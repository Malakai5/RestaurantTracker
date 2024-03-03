let foodDropDownList;

async function getFoodTypes() {
    // create a JSON object with parameters for API call and store in a variable
    let raw = JSON.stringify({
        "object_type":"column",
        "key":"food_type",
        "table":"restaurant"});
    let requestOptions = {
        method: 'POST',
        body: raw,
        redirect: 'follow'
    };

    foodDropDownList = document.querySelector("#foodType");

    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
        .then(response => response.text())
        .then(response => response.replaceAll("\"",""))
        .then(response => response.substring(1, response.length - 1))
        .then(response => response.split(","))
        .then(response => response.forEach( foodType => foodDropDownList.add(new Option(foodType))))
        .catch(error => alert('error'));

}
async function getCities(){
    try{
        let city = document.querySelector("#city");

        let selectBox = document.getElementById("state");
        let selectedValue = selectBox.options[selectBox.selectedIndex].value;
        let raw = JSON.stringify({
            "object_type":"cities",
            "key": selectedValue});
        let requestOptions = {
            method: 'POST',
            body: raw,
            redirect: 'follow'
        };
        fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
            .then(response => response.text())
            .then(response => response.replaceAll("\"",""))
            .then(response => response.substring(1, response.length - 1))
            .then(response => response.split(","))
            .then(response => response.forEach( foodType => city.add(new Option(foodType))))
            .catch(error => alert('error'));
    }
    catch (error)
    {
        console.error(error);
    }
}
async function searchButtonClicked(elementId){

    let data = new FormData(document.getElementById(elementId));
    let objectType = "consumable";
    let object = {};
    data.forEach((value, key) => object[key] = value);
    if (elementId === "restaurantSearchForm"){
        objectType = "restaurant"
    }

    let raw = JSON.stringify({
        "object_type":objectType,
        "item": object});
    let requestOptions = {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: raw,
        redirect: 'follow'
    };

    console.log(raw)

     fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/search", requestOptions)
         .then(response => response.text())
         .then(text => JSON.parse(text))
         .then(json =>{
             if (elementId === "consumableSearchForm"){
                 populateConsumableSearchResults(json)
             }
             else{
                 populateRestaurantSearchResults(json)
             }
         })


}
function populateConsumableSearchResults(jsonObject) {
    let resultsBox = document.getElementById("consumableSearchResults");
    resultsBox.innerHTML = "";
    for (let i = 0; i < jsonObject.length;i++) {
        let currentObject = jsonObject[i]
        let consumable = JSON.parse(currentObject.consumable)
        let restaurant = JSON.parse(currentObject.restaurant)

        resultsBox.innerHTML += "<h1><b>" + consumable.consumable_name + "</b>" + "<span class=\"w3-right w3-tag w3-dark-grey w3-round\">" + consumable.price + "</span></h1>"
        resultsBox.innerHTML += "<p class=\"w3-text-grey\">"
            + consumable.consumable_type + " | "
            + consumable.taste_elements + " | "
            + "</p>\n"
        getLegendIcons(resultsBox, consumable)
        resultsBox.innerHTML += "<br>Restaurant Name: " +
            "<a id='popupLink' onclick=(showPopup(" + restaurant.location_id + ")) href=\"#popup-box\">\n" +
            restaurant.restaurant_name +
            "</a>"
        resultsBox.innerHTML += "<hr style='border-color: black; border-width: 10px'>"
    }
}

function populatePopupMap(locationID){
    let raw = JSON.stringify({
        "object_type":"location",
        "key": locationID});
    let requestOptions = {
        method: 'POST',
        body: raw,
        redirect: 'follow'
    };

    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(json => {
            let location = JSON.parse(json)
            console.log(location)
            document.getElementById("location-input").value = location.address_number + " " + location.street_name
            document.getElementById("unit_number").value = location.unit_number
            document.getElementById("locality-input").value = location.city
            document.getElementById("administrative_area_level_1-input").value = location.state
            document.getElementById("postal_code-input").value = location.zipcode
            document.getElementById("country-input").value = "USA"
        })
}

function populateRestaurantSearchResults(jsonObject){
    let resultsBox = document.getElementById("restaurantSearchResults");
    resultsBox.innerHTML = "";

    for (let i = 0; i < jsonObject.length;i++){
        let currentJsonObject = jsonObject[i]
        let currentRestaurant = JSON.parse(currentJsonObject.restaurant)
        let currentLocation = JSON.parse(currentJsonObject.location)
        resultsBox.innerHTML += "<h1><b>" + currentRestaurant.restaurant_name + "</b>" + "<span class=\"w3-right w3-tag w3-dark-grey w3-round\">" + currentRestaurant.price_range + "</span></h1>"
        resultsBox.innerHTML += "<p class=\"w3-text-grey\">"
            + currentRestaurant.food_type + " | "
            + currentLocation.address_number + " " + currentLocation.street_name + ", "
            + currentLocation.city + ", " + currentLocation.state + " "
            + currentLocation.zipcode
            + "</p>\n"
        getLegendIcons(resultsBox, currentRestaurant)

    }
}

function getLegendIcons(element, jsonObject){
    if (jsonObject.is_hot === 1)
        element.innerHTML += "🔥";
    if (jsonObject.is_spicy === 1)
        element.innerHTML += "🌶";
    if (jsonObject.has_meat === 0)
        element.innerHTML += "🥦";
    if (jsonObject.has_dairy === 1)
        element.innerHTML += "🍦";
    if (jsonObject.is_favorite === 1)
        element.innerHTML += "🤤";
}

function showPopup(locationID){
    populatePopupMap(locationID)
    let popup = document.getElementById("popup-box")
    popup.style.visibility = "visible"
}
function closePopup(){
    let popup = document.getElementById("popup-box")
    window.location.href = "#"
    popup.style.visibility = "hidden"
}

