// DOM #main div element
var main = document.getElementById('main');

// **** Your JavaScript code goes here ****

//Paste your characters array from Activity 0


// Activity 1.1
// halfSurvival takes in character and returns 50% of the character's probability_of_survival
function halfSurvival(character) {
    return // TO DO: Return half of survival probability
}

// TO DO: For loop to call on halfSurvival function on all of your characters but one (the one you chose)


// Activity 1.2
// debugCharacters, loops through an array of characters and logs their name and the new probability of survival


// Call debug characters


// Activity 1.3

function addCharacterToDom(character) {
  // TO DO: Create new <div> element

  // Append the newly created <div> element to #main

  // Create a new <h5> element for characters' name

  // Append the newly created <h5> element to your new div

  // Set the textContent to the character's name


  // Do the same for house, probability of survival, and status using <p> element instead of <div> element

}

// Add a header to the DOM
// Create a new <h3> element
var header = document.createElement("h3");
// Append the newly created <h3> element to #main
main.appendChild(header);
// Set the textContent to:
header.textContent = "My Favorite GoT Characters";

// TO DO: For-loop on characters array, call addCharacterToDom method for each character
