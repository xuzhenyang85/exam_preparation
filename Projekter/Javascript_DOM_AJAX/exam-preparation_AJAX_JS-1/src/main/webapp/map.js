var map = document.getElementById("map");

map.addEventListener("click", toScreen);

//global variabel
var preSelection;

function toScreen(obj) {
    // Get obj's ID
    var selection = obj.target.id;
    alert("target id " +selection);
    preSelection = obj;

    obj.target.style = "fill:#ff0000";
    
        // if has clicked
    if (preSelection !== undefined) {
        //reset color
        preSelection.target.style = "fill:#c0c0c0";
    };
    
    var splitSelect = selection.split("-");
    getCountry(splitSelect[0]);
}

function getCountry(country) {
    
    var infoBox = document.getElementById("countryInfo");

    //Use the proxy
    var url = "http://restcountries.eu/rest/v1/alpha?codes=" + country;
    
    //Variable
    var conf = {
        //Property : Value
        method: 'get'
    };

    var promise = fetch(url, conf); //modtager info

    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        var country = JSON.parse(text); //fra JSON to objekt
        infoBox.innerHTML = "<b>Country:</b>" + country[0].name + "<br>" +
                "<b>Population:</b>" + country[0].surname + "<br>" +
                "<b>Area:</b>" + country[0].area + "<br>" +
                "<b>Borders:</b>" + country[0].borders.toString() + "<br>";
    });

}
