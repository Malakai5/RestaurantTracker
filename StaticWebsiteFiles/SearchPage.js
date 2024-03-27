let foodDropDownList;
let foodReadOnlyList
let accessLevel = '0'

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
    foodReadOnlyList = document.getElementById("typeList")
    // console.log(foodReadOnlyList.type)

    fetch("https://y629tb85u6.execute-api.us-east-1.amazonaws.com/dev/filter", requestOptions)
        .then(response => response.text())
        .then(text => JSON.parse(text))
        .then(json => json.body)
        .then(body => body.forEach( foodType => {
            foodDropDownList.add(new Option(foodType));
            foodReadOnlyList.appendChild(new Option(foodType))
        }))
        .catch(error => alert(error));

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
            .then(text => JSON.parse(text))
            .then(json => json.body)
            .then(json => json.forEach( foodType => city.add(new Option(foodType))))
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
         .then(json => json.body)
         .then(body =>{
                 populateRestaurantSearchResults(body)
         })
}

function giveStars(rating){
    let resultString = "";
    if (rating === 0){
        resultString = "💩"
    }
    for (let i = 0; i < rating; i++) {
        resultString += "⭐"
    }
    return resultString
}

function populateRestaurantSearchResults(jsonObject){
    let resultsBox = document.getElementById("restaurantSearchResults");
    resultsBox.innerHTML = "";

    console.log(jsonObject)

    for (let i = 0; i < jsonObject.length;i++){
        let currentJsonObject = jsonObject[i]
        let currentRestaurant = JSON.parse(currentJsonObject.restaurant)
        let currentLocation = JSON.parse(currentJsonObject.location)
        resultsBox.innerHTML += "<h1><b>" + currentRestaurant.restaurant_name + "</b>"
            + "<span class=\"w3-right w3-tag w3-dark-grey w3-round\">" +
            currentRestaurant.price_range + "</span></h1>"
        resultsBox.innerHTML += "<p class=\"w3-text-grey\">"
            + currentRestaurant.food_type + " | "
            + currentLocation.address_number + " " + currentLocation.street_name + ", "
            + currentLocation.city + ", " + currentLocation.state + " "
            + currentLocation.zipcode
            + "</p>\n"
        resultsBox.innerHTML += "<p> " + giveStars(currentRestaurant.rating) + " </p>"
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

let currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab

function showTab(n) {
    // This function will display the specified tab of the form ...
    let x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    // ... and fix the Previous/Next buttons:
    if (n === 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n === (x.length - 2)) {
        document.getElementById("nextBtn").style.display = "none";
        document.getElementById("submitBtn").style.display = "inline"
    }
    else {
        document.getElementById("submitBtn").style.display = "none";
        document.getElementById("nextBtn").style.display = "inline";
        document.getElementById("nextBtn").innerHTML = "Next";
    }
    if (n === (x.length - 1)) {
        document.getElementById("nextBtn").style.display = "none";
        document.getElementById("submitBtn").style.display = "none";
        document.getElementById("prevBtn").style.display = "none";
    }
    // ... and run a function that displays the correct step indicator:
    fixStepIndicator(n)
}

function hideTab(currentTab){
    let x = document.getElementsByClassName("tab");
    x[currentTab].style.display = "none"
}

function nextPrev(n) {
    // This function will figure out which tab to display
    let x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if (n == 1 && !validateForm()) return false;
    // Hide the current tab:
    x[currentTab].style.display = "none";
    // Increase or decrease the current tab by 1:
    currentTab = currentTab + n;
    // if you have reached the end of the form... :
    if (currentTab >= x.length) {
        //...the form gets submitted:
        // document.getElementById("entryForm").submit();
        return false;
    }
    // Otherwise, display the correct tab:
    showTab(currentTab);
}

function validateForm() {
    // This function deals with validation of the form fields
    let x, y, i, valid = true;
    x = document.getElementsByClassName("tab");
    y = x[currentTab].getElementsByClassName("entry-input");
    // A loop that checks every input field in the current tab:
    for (i = 0; i < y.length; i++) {
        // If a field is empty...
        if (y[i].value === "") {
            // add an "invalid" class to the field:
            if (!y[i].className.match(" invalid")){
                y[i].className += " invalid";
            }
            // and set the current valid status to false:
            valid = false;
        }
        else {
            if (y[i].className.match(" invalid")){
                y[i].className = y[i].className.replace(" invalid","")
            }
            document.getElementById(y[i].id + "-confirmation").value += y[i].value
            valid = true
        }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
        document.getElementsByClassName("step")[currentTab].className += " finish";
    }
    return valid; // return the valid status
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    let i, x = document.getElementsByClassName("step");
    console.log(x.length)
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace(" active", "");

    }
    //... and adds the "active" class to the current step:
    x[n].className += " active";
}

let accessCodeInput = document.getElementById("access-code")

function checkReviewerCode(){
    let accessCodeLevel = document.getElementById("reviewer-level")
    if (accessCodeInput.value === "BushBaby"){
        accessCodeLevel.value += "4"
        accessLevel = '4'
        alert("BushBaby 4EVAAAAAAA")
        accessCodeLevel.style.display = "inline-block"
    }
    if (accessCodeInput.value === "SilkSonic"){
        accessCodeLevel.value += "3"
        accessLevel = '3'
        alert("Powda nose")
        accessCodeLevel.style.display = "inline-block"
    }
    if (accessCodeInput.value === "DreadLocks"){
        accessCodeLevel.value += "2"
        accessLevel = '2'
        alert("Stay Locked")
        accessCodeLevel.style.display = "inline-block"
    }
    if (accessCodeInput.value === "Flawda"){
        accessCodeLevel.value += "1"
        accessLevel = '1'
        alert("Flawda gits united")
        accessCodeLevel.style.display = "inline-block"
    }
}
accessCodeInput.addEventListener("onchange",checkReviewerCode)



function newLocation(){
    let sqlStatement = "INSERT INTO location_table(state, city, zipcode, street_name, address_number, unit_number) VALUES ("
    let data = new FormData(document.getElementById("entryForm"));
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
        })
}

function splitAddress(address){
    let addressSplit = []
    addressSplit[0] = address.substring(0, address.indexOf(" "))
    addressSplit[1] = address.substring(address.indexOf(" ") + 1)
    return addressSplit
}

async function newRestaurant(){
    let sqlStatement = "INSERT INTO restaurant_table(restaurant_name, food_type, price_range, rating," +
        " location_id, is_local, poc_food, themed, highest_reviewer) VALUES (";
    let data = new FormData(document.getElementById("entryForm"));
    let locationID = await newLocation();
    let restaurant = {
        restaurant_name : data.get("restaurant_name"),
        food_type : data.get("food_type"),
        price_range : data.get("price_range"),
        rating : data.get("rating"),
        location_id : locationID.id,
        is_local : data.get("locally-owned"),
        poc_food : data.get("poc-food"),
        themed : data.get("themed"),
        highest_reviewer : accessLevel
    }

    console.log(restaurant)

    console.log(sqlStatement)

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
        .then(json =>{
            console.log(json)
            return json
        })
        .then(json => {
            if (json.statusCode === '200'){
                // document.getElementById("restaurantEntry").reset()
            }
        })
}

function restartForm(){
    hideTab(currentTab)
    currentTab = 0
    accessLevel = 0
    showTab(currentTab)
}
