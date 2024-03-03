function revealElement(elementId){
    let element = document.getElementById(elementId)
    if (element.style.display === "none" || element.style.display === "") {
        element.style.display = "block";
    }
    else {
        element.style.display = "none";
    }
}

function showAndHide(elementToShow, elementToHide){
    let show = document.getElementById(elementToShow)
    let hide = document.getElementById(elementToHide)

    show.style.display = "block"
    hide.style.display = "none"
}


function checkConsumableType() {
    let select = document.getElementById("consumableType");
    let consumableType = select.value
    if (consumableType === "Entree"){
        document.getElementById("tasteElement").style.display="table-row";
        document.getElementById("mealSelect").style.display="table-row";
        document.getElementById("isSpicy").style.display="table-row";
        document.getElementById("alcoholSelect").style.display="none";
        document.getElementById("mainIngredients").style.display="table-row";
        document.getElementById("isShareable").style.display="none";
        document.getElementById("numberOfSides").style.display="table-row";
        document.getElementById("cookingMethod").style.display="table-row";
        document.getElementById("caffeineSelect").style.display="none";
    }
    else if (consumableType === "Appetizer"){
        document.getElementById("tasteElement").style.display="table-row";
        document.getElementById("mealSelect").style.display="table-row";
        document.getElementById("isSpicy").style.display="table-row";
        document.getElementById("alcoholSelect").style.display="none";
        document.getElementById("mainIngredients").style.display="table-row";
        document.getElementById("isShareable").style.display="table-row";
        document.getElementById("numberOfSides").style.display="none";
        document.getElementById("cookingMethod").style.display="table-row";
        document.getElementById("caffeineSelect").style.display="none";
    }
    else if (consumableType === "Drink"){
        document.getElementById("tasteElement").style.display="table-row";
        document.getElementById("mealSelect").style.display="none";
        document.getElementById("isSpicy").style.display="table-row";
        document.getElementById("alcoholSelect").style.display="table-row";
        document.getElementById("mainIngredients").style.display="none";
        document.getElementById("isShareable").style.display="none";
        document.getElementById("numberOfSides").style.display="none";
        document.getElementById("cookingMethod").style.display="none";
        document.getElementById("caffeineSelect").style.display="table-row";
    }
    else{
        document.getElementById("tasteElement").style.display="none";
        document.getElementById("mealSelect").style.display="none";
        document.getElementById("isSpicy").style.display="none";
        document.getElementById("alcoholSelect").style.display="none";
        document.getElementById("mainIngredients").style.display="table-row";
        document.getElementById("isShareable").style.display="none";
        document.getElementById("numberOfSides").style.display="none";
        document.getElementById("cookingMethod").style.display="none";
        document.getElementById("caffeineSelect").style.display="none";
    }
}