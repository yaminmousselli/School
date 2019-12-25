// JavaScript code goes here

// Activity 0.3
// Declare local characters array

var char_array = ['H', 'E', 'L', 'L', 'O'];


// Activity 0.3
// print the entire characters array using console.log(), Web Console will show an expandable version of the array
console.log(char_array);

// Activity 0.4
// Name of the first character
console.log(char_array[0]);

// All the affiliated houses of the 2nd character
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
for (var i=0; i < gameOTCharacters.length; i++) {
    console.log(gameOTCharacters[i].house_affiliations);
}
// The status of the last character
console.log(gameOTCharacters[3].status);

// If any of the characters are at the same location (true or false)
for (var i=0; i < gameOTCharacters.length; i++) {
    let nextCharacter = ++i;
    if (gameOTCharacters[i].current_location ===
        gameOTCharacters[nextCharacter].current_location) {
                var toBeReturned = true;
                console.log(toBeReturned);
        }
}
// The mean probability of all the characters' survival
for (let i=0; i < gameOTCharacters.length; i++) {
    var survivalMean = 0;
    survivalMean += gameOTCharacters[i].probability_of_survival;
    if (i= gameOTCharacters.length-1) {
        survivalMean = survivalMean / (i + 1);
        console.log(survivalMean);
    }
}
var myName = "Yamin Mousselli";
console.log(myName);
