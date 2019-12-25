#Programming Quiz 2
## TV Series

I recommend you read through all of the required classes before you start coding. It may simplify your code by knowing which classes need each piece of information.
You'll be writing 5 classes:

### TvSeries
* Should __NOT__ be able to make an instance of this class
* TvSeries should have the following instance data that are initialized in the constructor. The parameters to the constructor are in the following order:
	* String title
	* int episodeLength
	* int numberEpisodes
* You need methods to get the above instance variables and these methods should be the only way to access these variables. You should not have setter methods.
* You should not be able to change their values after the object has been created.
* TvSeries should override Object's toString method:
	* It should return a string of the format "`title` has `numberEpisodes` episodes where each episode is `episodeLength` minutes long."

### Bingeable
* This should be an interface
* The only method it needs to have is:
    * `binge` which will have a `String` return type
        * This method will return a string which states:
            * "You binge watch `title`. It takes `numberEpisodes * episodeLength` minutes to binge."
        * To be very clear: you are not printing out the __*__ above. That is a multiplication symbol.

### NetflixTvSeries
* This is a subclass of TvSeries
* This is an implementation of Bingeable

## Creating Files
All of the files you write should be created in `src/main/java`. Currently there
are three empty files, `TvSeries.java`, `NetflixTvSeries.java`, and `Bingeable.java`, in this directory. To complete this programming quiz you must fill out these two empty files.

## Testing
This directory is setup to utilize gradle.
* From the root directory, the directory with this file, you may run a few commands:
	* gradle build
	* gradle test
	* gradle checkstyle
* These commands should compile and execute the code.
* If it didn't compile:
	* First: Check that your code compiles by itself. Just because you have gradle
	doesn't mean you have to use it. Be sure to use javac on your files as you go.
	* Second: If gradle still isn't compiling but your code compiles then you haven't
	completed all of the classes/methods necessary for the tests to run.
		* You can fix this by creating skeleton code that prints out dummy values. This
		allows you to test the code you've already written.

## Submission
You will be submitting 3 files to T-Square:
* `TvSeries.java`
* `Bingeable.java`
* `NetflixTvSeries.java`

