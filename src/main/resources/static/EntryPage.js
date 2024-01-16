let restaurantOptions;

function newEntry(elementId){
    let data = new FormData(document.getElementById(elementId));
    let object = {};
    data.forEach((value, key) => object[key] = value);

    fetch('http://192.168.1.93:8080/entry/' + elementId,{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(object)
    })
}

//TODO Figure out the promise stuff
async function getRestaurantOptions() {
    let selectedValue = document.getElementById("restaurantName").value;
    let response = await fetch("http://192.168.1.93:8080/entry/restaurantOptions?name=" + selectedValue);
    restaurantOptions = await JSON.parse(await response.text());
    restaurantOptions.forEach(restaurant => document.querySelector("#restaurantOptions").add(new Option(restaurant.Name)))
}

let restaurantNameBox = document.getElementById("restaurantName");
restaurantNameBox.addEventListener("change", getRestaurantOptions)