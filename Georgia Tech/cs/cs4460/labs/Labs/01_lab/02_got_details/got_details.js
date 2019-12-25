// DOM #main div element
var main = document.getElementById('main');

// **** Your JavaScript code goes here ****

//Paste your characters array from Activity 0
var gameOTCharacters = [
    {
        name: "Jon Snow",
        status: "Alive... Again",
        current_location: "Winterfell",
        power_ranking: 3,
        house: "stark",
        house_affiliations: "stark",
        probability_of_survival: 84
        //[Jon Snow,Alive… Again,Winterfell,3,stark,stark,84]
    },
    {
        name: "Davos Seaworth",
        status: "Alive",
        current_location: "Winterfell",
        power_ranking: -1,
        house: "stark",
        house_affiliations: "stark, baratheon",
        probability_of_survival: 78
//[Davos Seaworth,Alive,Winterfell,-1,stark,"stark, baratheon",78]
    },
    {
        name: "Tyrion Lannister",
        status: "Alive",
        current_location: "Way to Westeros",
        power_ranking: 4,
        house: "targaryen",
        house_affiliations: "targaryen, lannister",
        probability_of_survival: 70
//[Tyrion Lannister,Alive,Way to Westeros,4,targaryen,"targaryen, lannister",70]
    },
    {
        name: "Daenerys Targaryen",
        status: "Alive",
        current_location: "Way to Westeros",
        power_ranking: 1,
        house: "targaryen",
        house_affiliations: "targaryen",
        probability_of_survival: 99
        //[Daenerys Targaryen,Alive,Way to Westeros,1,targaryen,targaryen,99]];
    }
];
// Activity 1.1
// halfSurvival takes in character and returns 50% of the character's probability_of_survival
function halfSurvival(character) {
    var halfProbOfSurvival = 0;
    for(var i=0; i < gameOTCharacters.length;i++) {
        if (character == gameOTCharacters[i].name) {
            halfProbOfSurvival = gameOTCharacters[i].probability_of_survival / 2;
        }
    }
    return halfProbOfSurvival;
    
    //below is the solution, much simpler
    //return character['probability_of_survival'] / 2;

}

// TO DO: For loop to call on halfSurvival function on all of your characters but one (the one you chose)
halfSurvival = function(character) {
    //way simpler solution in solution
    var halfProbOfSurvival_2 = 0;
    for(var i=0; i < gameOTCharacters.length;i++) {
        if (gameOTCharacters[i].name == "Daenerys Targaryen") {
            continue;
        } else {
            halfProbOfSurvival_2 = gameOTCharacters[i].probability_of_survival / 2;
            console.log(halfProbOfSurvival_2);
        }
    }
    return halfProbOfSurvival_2;
}

// Activity 1.2
// debugCharacters, loops through an array of characters and logs their name and the new probability of survival.

// Call debug characters
function debugCharacters() {
    //did this wrong, check solution
    for(var i=0; i < gameOTCharacters.length; i++) {
        console.log(gameOTCharacters[i].name);
        console.log(halfSurvival(gameOTCharacters[i].name));
    }
    return;
}
// Call debug characters
debugCharacters();

// Activity 1.3

function addCharacterToDom(character) {

  // TO DO: Create new <div> element

  // Append the newly created <div> element to #main

  // Create a new <h5> element for characters' name

  // Append the newly created <h5> element to your new div

  // Set the textContent to the character's name

  // Do the same for house, probability of survival, and status using <p> element instead of <div> element

  var newDiv = document.createElement("div");
  main.appendChild(newDiv);

  var characterNameHeader = document.createElement("h4");
  main.appendChild(characterNameHeader);
  //might need to change how i access name below.
  characterNameHeader.textContent = gameOTCharacters[i]["name"];

  var status = document.createElement("p");
  newDiv.appendChild(status);
  status.textContent = "Status: " + gameOTCharacters[i]["status"];

  var current_location = document.createElement("p");
  newDiv.appendChild(current_location);
  current_location.textContent = "Current Location: " + gameOTCharacters[i]["current_location"];

  var power_ranking = document.createElement("p");
  newDiv.appendChild(power_ranking);
  power_ranking.textContent = "Power Ranking: " + gameOTCharacters[i]["power_ranking"];

  var house = document.createElement("p");
  newDiv.appendChild(house);
  house.textContent = "House: " + gameOTCharacters[i]["house"];

  var house_affiliations = document.createElement("p");
  newDiv.appendChild(house_affiliations);
  house_affiliations.textContent = "House Affiliations: " + gameOTCharacters[i]["house_affiliations"];

  var probability_of_survival = document.createElement("p");
  newDiv.appendChild(probability_of_survival);
  probability_of_survival.textContent = "Probability of Survival: " + gameOTCharacters[i]["probability_of_survival"] + "%";
}

// Add a header to the DOM

// document is the DOM, select the #main div
var main = document.getElementById("main");
// Create a new <h3> element / DOM element
var header = document.createElement("h3");
// Append the newly created <h3> element to #main
main.appendChild(header);
// Set the textContent to:
header.textContent = "GoT Character Attributes";

// TO DO: For-loop on characters array, call addCharacterToDom method for each character
for(var i=0; i < gameOTCharacters.length; i++) {
    addCharacterToDom(gameOTCharacters[i]);
}


//GOT THE BELOW FROM THE PDF, I've implemented what I have learned // in addCharacterToDom()

// Create a new <div> element
//var div1 = document.createElement("div");

// Append the newly created <div> element to #main
//main.appendChild(div1);

// Create a new <h5> element
//var name1 = document.createElement("h5");

// Append the newly created <h5> element to your new div
//div1.appendChild(name1);

// Set the textContent to the first characters name
//name1.textContent = gameOTCharacters[0]["name"];

// Create a new <p> element
//var survival1= document.createElement("p");

// Append the newly created <p> element to your new div
//div1.appendChild(survival1);

// Set the textContent to the first characters survival prob.
//survival1.textContent = "Survival %: " //+gameOTCharacters[0]["probability_of_survival"] +"%";
