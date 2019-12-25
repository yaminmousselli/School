---
title: pq-carbase
---

# Carbase Programming Quiz

In this programming quiz you will write a class to represent cars, and a class that implements a simple database for them.

1. Download the zip file of this repository to the directory on your disk where you keep your CS 1331 course work.

2. Unzip the zip archive and navigate to the directory created by unzipping the archive. You should see a ``build.gradle`` file in this directory. This is the *project root*. From this directory the files you will edit are in src/main/java.

3. Implement the following constructors and methods in ``Car`` :

  - a constructor that takes two ``String`` parameters and one ``int`` parameter.
    - The first parameter is the year of the car and should be stored in an instance variable.
    - The second parameter is the make of the car and should be stored in an instance variable.
    - The third parameter is the model of the car and should be stored in an instance variable.
  - ``public String toString()`` returns a ``String`` representation of a ``Car`` object in the form ``I love my <year> <make> <model>``. For example, ``new Car(2017, "Nissan", "Gr 8").toString()`` would return ``I love my 2017 Nissan GT-R``.
  - ``public String getMake()`` returns the make of the car.
  - ``public String getModel()`` returns the model of the car.
  - ``public int getYear()`` returns the year of the car.
  - The instance data should only be accessible through these getters

4. Implement the following constructors and methods in ``Carbase`` :

  - a constructor that takes a variable number of single ``Car`` parameters. The ``Car`` objects (INSTANCES OF CAR) passed as arguments to the constructor should be stored in an array instance variable.
  - ``public Car findByModel(String model)`` returns the first ``Car`` instance stored in the array instance variable whose model matches ``model``. If no Car in the array matches, return ``null``.
  - ``public Car findByYear(int year)`` returns the first ``Car`` instance stored in the array instance variable whose year matches ``year``. If no Car in the array matches, return ``null``.

## Tips:

- First, create your ``Car`` and ``Carbase`` classes and stub out all the required contructors and methods with dummy return values. Then go back and fill in implementations. ("//TODO" comments may help you remember what you need to come back to.)
- Run ``gradle -q test`` to get a report of test failures. This should open in a window like checkstyle. (If it fails before the window is opened with errors on the command line, this means that the tests themselves did not compile. Read the error meesages carefully and make sure your methods and constructors are named and formatted correctly!)
  - If the report does *not* say "100% successful", then click through each failure and pay close attention to the **first** line of the error - this will tell you what is wrong.
- As you add functionality, rerun the previous step and re-submit if your grade is improved.
- Look at the test code in ``src/test/java`` to see how your class is used.
- You are NOT allowed to use the ``ArrayList`` or ``Arrays`` classes.

## Submission

You will be submitting this programming quiz on T-Square. Once you have completed your work, recompress the folder (zip it) by right clicking on the folder in your file explorer and selecting "compress."  Submit the folder under the Assignments tab on T-Square.
After you have submitted make sure that you recieved an email verifying your submission.


## Files to submit

        src/main/java/Car.java
        src/main/java/Carbase.java

### Submissions that do not compile will recieve a zero!
This means the entire submission. Make sure every Java file that is submitted compiles successfully!
