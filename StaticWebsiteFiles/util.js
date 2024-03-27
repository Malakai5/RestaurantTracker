// let resSubmitButton = document.getElementById("restaurantSubmitButton")
// resSubmitButton.disabled = true
// let conSubmitButton = document.getElementById("consumableSubmitButton")
// conSubmitButton.disabled = true

function revealElement(elementId) {
    let element = document.getElementById(elementId)
    if (element.style.display === "none" || element.style.display === "") {
        element.style.display = "block";
    } else {
        element.style.display = "none";
    }
}

function showAndHide(elementToShow, elementToHide) {
    let show = document.getElementById(elementToShow)
    let hide = document.getElementById(elementToHide)

    show.style.display = "block"
    hide.style.display = "none"
}


function checkConsumableType() {
    let select = document.getElementById("consumableType");
    let consumableType = select.value
    if (consumableType === "Entree") {
        document.getElementById("tasteElement").style.display = "table-row";
        document.getElementById("mealSelect").style.display = "table-row";
        document.getElementById("isSpicy").style.display = "table-row";
        document.getElementById("alcoholSelect").style.display = "none";
        document.getElementById("mainIngredients").style.display = "table-row";
        document.getElementById("isShareable").style.display = "none";
        document.getElementById("numberOfSides").style.display = "table-row";
        document.getElementById("cookingMethod").style.display = "table-row";
        document.getElementById("caffeineSelect").style.display = "none";
    } else if (consumableType === "Appetizer") {
        document.getElementById("tasteElement").style.display = "table-row";
        document.getElementById("mealSelect").style.display = "table-row";
        document.getElementById("isSpicy").style.display = "table-row";
        document.getElementById("alcoholSelect").style.display = "none";
        document.getElementById("mainIngredients").style.display = "table-row";
        document.getElementById("isShareable").style.display = "table-row";
        document.getElementById("numberOfSides").style.display = "none";
        document.getElementById("cookingMethod").style.display = "table-row";
        document.getElementById("caffeineSelect").style.display = "none";
    } else if (consumableType === "Drink") {
        document.getElementById("tasteElement").style.display = "table-row";
        document.getElementById("mealSelect").style.display = "none";
        document.getElementById("isSpicy").style.display = "table-row";
        document.getElementById("alcoholSelect").style.display = "table-row";
        document.getElementById("mainIngredients").style.display = "none";
        document.getElementById("isShareable").style.display = "none";
        document.getElementById("numberOfSides").style.display = "none";
        document.getElementById("cookingMethod").style.display = "none";
        document.getElementById("caffeineSelect").style.display = "table-row";
    } else {
        document.getElementById("tasteElement").style.display = "none";
        document.getElementById("mealSelect").style.display = "none";
        document.getElementById("isSpicy").style.display = "none";
        document.getElementById("alcoholSelect").style.display = "none";
        document.getElementById("mainIngredients").style.display = "table-row";
        document.getElementById("isShareable").style.display = "none";
        document.getElementById("numberOfSides").style.display = "none";
        document.getElementById("cookingMethod").style.display = "none";
        document.getElementById("caffeineSelect").style.display = "none";
    }
}

function enableRestaurantSubmitButton() {
    let submitButton = document.getElementById("restaurantSubmitButton")
    let regExp = new RegExp("[a-zA-Z0-9_&' ]*$")
    let valid = true
    if (!regExp.test(document.getElementById("resName").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("foodType").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("location-input").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("locality-input").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("administrative_area_level_1-input").value)) {
        valid = false
    }

    if (valid === false) {
        submitButton.style.backgroundColor = "gray"
        submitButton.enabled = false
    } else {
        submitButton.style.backgroundColor = "lightgray"
        submitButton.enabled = true
        alert("WE JERERE")
    }
}

function enableConsumableSubmitButton() {
    let submitButton = document.getElementById("restaurantSubmitButton")
    let regExp = new RegExp("[a-zA-Z0-9_&' ]*$")
    let valid = true
    if (!regExp.test(document.getElementById("conName").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("conFoodType").value)) {
        valid = false
    }
    if (!regExp.test(document.getElementById("restaurantName").value)) {
        valid = false
    }
    if (document.getElementById("consumableType").value !== "Drink") {
        if (!regExp.test(document.getElementById("mainIngredients").value)) {
            valid = false
        }
    } else if (document.getElementById("cookingMethod").value === "Appetizer" ||
        document.getElementById("cookingMethod").value === "Entree") {
        if (!regExp.test(document.getElementById("cookingMethod").value)) {
            valid = false
        }
    }
    if (valid === false) {
        submitButton.style.backgroundColor = "gray"
        submitButton.enabled = false
    } else {
        submitButton.style.backgroundColor = "lightgray"
        submitButton.enabled = true
    }
}

function resetForm(formID) {
    let form = document.getElementsByName(formID);
    alert("Lets start resettin")
    form.reset();  // Reset all form data
}


