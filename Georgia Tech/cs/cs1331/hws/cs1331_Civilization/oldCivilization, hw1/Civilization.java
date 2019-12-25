/*
Name: Yamin Mousselli
Teacher Name: Mr. Simpkins
Class: CS 1331, Section
Date: 15 September 2016
*/
//Static variable mean that you are updating every instance of that variable to whatever object is using it
//Static method means that you don't have to instantiate the class to use the method. 
import java.util.Scanner;

public class Civilization {
    private static boolean playing = true;
    private static String [] cityAry = new String [5];
    private static int numAttacks = 0;
    private static double gold = 20.5;
    private static double resources = 30.0;
    private static int happiness = 10;
    private static int miliUnits = 0;
    private static int techPoints;
    private static String characterName;
    private static int numsCity;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please name your city: ");
        cityAry[0] = in.next();
        ++numsCity;
        //System.out.println("Your city name is: "+ Arrays.toString(cityAry));

        String user1 = "1) American (George Washington)";
        String user2 = "2) Zulu (Shaka)";
        String user3 = "3) English (Queen Elizabeth)";
        String user4 = "4) Chinese (Wu Zetian)";
        System.out.println("Please enter the number associated with the civilization you would like to lead with: "
            + "\n\t" + user1
            + "\n\t" + user2
            + "\n\t" + user3
            + "\n\t" + user4);
        int userInput = in.nextInt();//What's wrong with this?

        if (userInput == 1)
            characterName = "George Washington";
        else if (userInput == 2)
            characterName = "Shaka";
        else if (userInput == 3)
            characterName = "Queen Elizabeth";
        else
            characterName = "Wu Zetian";

        System.out.println("Your character name is: " + characterName);

        while (playing) {
            ++resources;
            if (happiness > 20) {
                for(int i = 0; i < cityAry.length; ++i ) {//length is a property of the array
                    if (cityAry[i] != null) {
                        resources += 5;
                        gold += 3;
                    }
                    if (resources % 2 == 0) {
                        ++happiness;
                    }
                    else{
                        happiness -= 3;
                    }
                }
            }
            System.out.println("Please enter the number associated with the action"
                + " you want to perform:\n1) Settle a City \n"
                + "2) Demolish a City \n3) Build Militia \n4)"
                + " Research Technology \n5) Attack Enemy Ship \n6) End Turn");
            int actionNum = in.nextInt();
            in.nextLine();//To eliminate the buffer
            switch(actionNum) {
                //Settle a City Option
                case 1:
                    if (numsCity > 4) {
                        System.out.println("You have reached the maximum amount of cities that you can have");
                        break;
                    }
                    if (gold < 15.5) {
                        System.out.println("You do not have enough gold to purchase a city");
                        break;
                    }
                    else {
                        gold -= 15.5;
                    }
                    for (String s : cityAry) {
                        //Dont print when s is null
                        System.out.println("\t" + s);
                    }
                    System.out.println("What would you like your new city to be: ");
                    int citySpot = -1;
                    for (int i = 0; i < cityAry.length && citySpot == -1; ++i) {
                        if (cityAry[i] == null) {
                            citySpot = i;
                        }
                    }
                    cityAry[citySpot] = in.nextLine();
                    ++numsCity;
                    break;
                //Demolish a City Option
                case 2:
                    if (numsCity < 2) {
                        System.out.println("You do not have enough cities to demolish a city.");
                        break;
                    }
                    else {
                        for (int i = 0; i < numsCity; ++i) {
                            System.out.println(cityAry[i]);
                        }
                        System.out.println("Please choose a city to demolish: ");
                        String cityName = in.nextLine();
                        for (int i = 0; i < numsCity; ++i) {
                            if (cityName.equals(cityAry[i])) {
                                cityAry[numsCity] = null;//set the city that they're demolishing to null
                                --numsCity;
                                for (int j = i; j < numsCity; --j) {
                                    cityAry[j] = cityAry[j +1];
                                }
                                cityAry[numsCity] = null;
                            }
                        }
                        resources += 1.5;
                    }
                    break;
                //Build Militia Option
                case 3:
                    if (gold >= 5 && resources >= 3) {
                        gold -=5;
                        resources -= 3;
                        miliUnits += 1;
                        break;
                    }
                    else if (gold < 5) {
                        System.out.println("You do not have enough gold to build your militia. Your turn is over.");
                    }
                    else if (resources < 3) {
                        System.out.println("You do not have enough resources to build your militia. Your turn is over.");
                    }
                    else {
                        break;
                    }
                //Research Technology Option
                case 4:
                    if (gold >= 50 && resources >= 2) {
                        gold -= 50;
                        resources -= 2;
                        techPoints += 1;
                    }
                    else if (gold < 50) {
                        System.out.println("You do not have enough gold to earn research technology. Your turn is over");
                    }
                    else if (resources < 2) {
                        System.out.println("You do not have enough resources to earn research technology. Your turn is over");
                    }
                    else {
                        break;
                    }
                //Attack Enemy City
                case 5:
                    ++numAttacks;
                    if (miliUnits >= 6) {
                        miliUnits -= 6;
                        gold += 10;
                        happiness -= 3;
                        break;
                    }
                    else if (miliUnits < 5) {
                        System.out.println("You do not have enough military units to attack an enemy city. Your turn is over");
                    }
                    else {
                        break;
                    }
                //End Turn Option
                default:
                    System.out.println("Your turn is over");
                }
                if (techPoints >= 20 || numAttacks == 10) {
                    System.out.println("You have won the game" + techPoints);
                    playing = false;
                }

            }

    }
}
