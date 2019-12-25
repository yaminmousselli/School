# Programming Quiz 3

## Animals
I recommend you read through all of the required classes before you start coding. It may simplify your code by knowing which classes need each piece of information.
You'll be editing 3 separate classes:


### Animal
- The constructor, instance data, getter methods, and toString method have all been given to you
- Your job is to define the natural ordering for Animal
	- The natural ordering will order Animals from slowest to fastest based on their `maxSpeed`
	- Implement any necessary methods
	- Make any necessary additions to the class header
- Finish the method `race(Animal o)`
	- This method should throw a `NumLegsAdvantageException` (which you will write in the given file NumLegsAdvantageException.java) if the Animal that is passed in does not have the same number of legs as the instance of animal the method was called on.
	- Otherwise, the method should return the animal that has the largest maxSpeed. If two animals have the same max speed you should return the instance of Animal that this method was called on.
	- Make any necessary additions to the method header
- Do not create any additional instance variables

### NumLegsAdvantageException
- This file is provided partially to you.
- A `NumLegsAdvantageException` is a checked exception. Make the appropriate changes to the file to reflect this.
- `NumLegsAdvantageException` should have one constructor in it that passes an error message up to its super class. The error message should be "An invalid number of legs was experienced. This race is unfair!".


### Driver
- This is the class we will use to run our program
- You have already been given 5 different Animals to work with
- What you need to do for this class:
	1. Create an ArrayList of Animals named `animals`
	2. Add all the Animals to `animals` in the order in which they were instantiated (ie a1, 	then a2, then a3, etc.)
	3. Sort `animals` based on their natural ordering
	5. Finally, race any two animals that have been instantiated and print out the winner (or the error message if a problem occurs).

### Notes
- All needed imports have been provided