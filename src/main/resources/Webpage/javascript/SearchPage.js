let foodDropDownList;

async function getFoodTypes() {
    try{
        let response = await fetch("http://localhost:8080/filter/foodType");
        let foodTypes = response.text();
        foodTypes = (await foodTypes).replaceAll("\"","")
        foodTypes = (await (foodTypes)).substring(1,(await (foodTypes)).length - 1);
        let foodList = (await (foodTypes)).split(",");
        foodDropDownList = document.querySelector("#foodType");
        foodList.forEach( foodType => foodDropDownList.add(new Option(foodType)));
    }
    catch (error)
    {
        console.error(error);
    }

}
async function getCities(){
    try{
        let selectBox = document.getElementById("state");
        let selectedValue = selectBox.options[selectBox.selectedIndex].value;
        let response = await fetch("http://localhost:8080/filter/cities?state=" + selectedValue);
        let cityOptions = response.text();
        cityOptions = (await cityOptions).replaceAll("\"","")
        cityOptions = (await (cityOptions)).substring(1,(await (cityOptions)).length - 1);
        let options = (await (cityOptions)).split(",");
        options.forEach(city => document.querySelector("#city").add(new Option(city)));
    }
    catch (error)
    {
        console.error(error);
    }
}
async function searchButtonClicked(elementId){

    let data = new FormData(document.getElementById(elementId));
    let object = {};
    data.forEach((value, key) => object[key] = value);

     fetch('http://localhost:8080/filter/' + elementId ,{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(object)
    })
        .then(response => response.json())
        .then(response =>{
            if (elementId === "consumableSearchForm"){
                populateConsumableSearchResults(response)
            }
            else{
                populateRestaurantSearchResults(response)
            }
        })


}
function revealElement(elementId){
    let element = document.getElementById(elementId)
    if (element.style.display === "none") {
        element.style.display = "block";
    }
    else {
        element.style.display = "none";
    }
}

function checkConsumableType() {
    let select = document.getElementById("consumableType");
    let consumableType = select.value
    if (consumableType === "Entree" || consumableType === "Appetizer"){
        document.getElementById("mealSelect").style.display="table-row";
        document.getElementById("alcoholSelect").style.display="none";
        document.getElementById("caffeineSelect").style.display="none";

    }
    else if (consumableType === "Drink"){
        document.getElementById("alcoholSelect").style.display="table-row";
        document.getElementById("caffeineSelect").style.display="table-row";
        document.getElementById("mealSelect").style.display="none";
    }
    else{
        document.getElementById("mealSelect").style.display="none";
        document.getElementById("alcoholSelect").style.display="none";
        document.getElementById("caffeineSelect").style.display="none";
    }


}

function populateConsumableSearchResults(jsonObject){
    let resultsBox = document.getElementById("consumableSearchResults");
    for (let i = 0; i < jsonObject.length;i++){
        console.log((jsonObject[i]));
        resultsBox.innerHTML += "<h1><b>" + jsonObject[i].name + "</b>" + "<span class=\"w3-right w3-tag w3-dark-grey w3-round\">" + jsonObject[i].price + "</span></h1>"
        resultsBox.innerHTML += "<p class=\"w3-text-grey\">"
            + jsonObject[i].consumableType + " | "
            + jsonObject[i].tasteElements + " | "
            + "</p>\n"
        getLegendIcons(resultsBox, jsonObject[i])
    }
}

function populateRestaurantSearchResults(jsonObject){
    let resultsBox = document.getElementById("restaurantSearchResults");
    for (let i = 0; i < jsonObject.length;i++){
        console.log((jsonObject[i]));
        resultsBox.innerHTML += "<h1><b>" + jsonObject[i].name + "</b>" + "<span class=\"w3-right w3-tag w3-dark-grey w3-round\">" + jsonObject[i].priceRange + "</span></h1>"
    }
}

function getLegendIcons(element, jsonObject){
    if (jsonObject.spicy === true)
        element.innerHTML += "🌶";
    if (jsonObject.hasMeat === false)
        element.innerHTML += "🥦";
    if (jsonObject.hasDairy === true)
        element.innerHTML += "🍦";
    if (jsonObject.favorite === true)
        element.innerHTML += "🤤";
}

