class MethodsTester {
    public static void main(String[] args) {
        Methods obj1 = new Methods();
        String greeting = "Hello World";
        obj1.speak(greeting); //You're passing this ARGUMENT to the method speak in the Methods class
        obj1.jumping(7);
        obj1.moving("West", 10.5); //These ARHUMENTS ARE ORDER SPECIFIC WHEN PASSING THEM IN AS PARAMETERS INTO THE METHOD

        Methods obj2 = new Methods();
        String notNice = "Eddie";
        obj2.speak(notNice);
        obj2.jumping(10);
        obj2.moving("North", 11.7);

    }
}
