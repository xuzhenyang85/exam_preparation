
var persons = document.getElementById("persons");

document.getElementById("persons").onload = getData(31,'denmark','female');

function getData(amount,region,gender) {
    //Use the proxy
    var url = "http://uinames.com/api/?amount="+amount+"&region="+region+"&gender="+gender;

    //Variable
    var conf = {
        //Property : Value
        method: 'get'
    };
    
    var promise = fetch(url, conf); //modtager info
    
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        var personData = JSON.parse(text);
        
        personData.forEach(function(obj){
            var personName =obj.name;
            var personSurname =obj.surname;
            var personGender =obj.gender;
            var personRegion =obj.region;
            persons.innerHTML += "<b>Person's name:</b> " + personName + "<br>" +
                "<b>Person's surname:</b> " + personSurname + "<br>" +
                "<b>Person's gender:</b> " + personGender    + "<br>" +
                "<b>Person's region:</b> " + personRegion + "<br>";
        });
    });
    
    
}