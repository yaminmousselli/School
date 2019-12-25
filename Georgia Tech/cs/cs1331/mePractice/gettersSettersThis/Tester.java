class Tester {
    public static void main(String[] args) {
        Frog frog1 = new Frog();
        /*Before we did this:
        frog1.name = "Froggie";
        frog1.age = 22;

        // ^^By doing this, we are displaying the object's state to the world. We need to make it hidden and we
        DON'T WANT TO WORRY ABOUT WHAT THE INSTANCE VARIABLES are FOR THAT CLASS.
        INSTEAD WE WANT TO USE SETTERS TO SET THEIR VALUE in a class so we can access
        the methods at all times in any class without having the headache of keeping track of the instance variables. */

        //frog1.setName("Froggie");
        //frog1.setAge(22);
        //System.out.print(frog1.age); //THIS IS A COMPILING ERROR BECAUSE age is private and you CAN NOT ACCESS age
        //outside the Frog class WITHOUT ACCESSING THE GETTER!!!
        //System.out.println(frog1.getAge());
        //System.out.print(frog1.getName());

        frog1.setInfo("Froggie", 22); //YOU CAN HAVE A METHOD THAT CALLS THE SETTERS...IT WILL DO THE SAME JOB AS PASSING THE ARGUMENTS INDIVIDUALLS TO EACH SETTER
        System.out.println(frog1.getAge() + ", " + frog1.getName());

        Frog frog2 = new Frog();
        //frog2.setName("Sammie");
        //frog2.setAge(44);*/
        frog2.setInfo("Sammie", 44);
        System.out.println(frog2.getName() + ", " + frog2.getAge());


        Frog frog3 = new Frog();
        frog3.setName("Harry");
        frog3.setAge(69);
    }
}
