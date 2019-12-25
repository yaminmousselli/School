    public class Carbase {
        //Car array instance variable
        Car[] carArray; //DECLARING AN ARRAY OF CAR OBJECT
        // new Car[]; //This is CREATING AN INSTANCE OF A CAR ARRAY THAT HOLDS CAR OBJECTS

        public Carbase(Car... myCars) {
            this.carArray = myCars; //This method creates an overall reference to the array containing all the objects.
                                    // The var args is technically creating an instance of an array of car objects behind the scenes.
        }

        //This block of code creates an individual pointer to from carArray to each object
        public Car findByModel(String model) {
            for (int i = 0; i < carArray.length; ++i) {
                if (model.equals(carArray[i].getModel())) {
                    return carArray[i];
                }
            } return null;
        }
        public Car findByYear(int year) {
            for (int i = 0; i < carArray.length; ++i) {
                if (year == carArray[i].getYear()) {
                    return carArray[i];
                }
            } return null;
        }
    }
