public class Tester {
    public static void main(String[] args){
        Machine mach1 = new Machine();
        Camera cam1 = new Camera();

        //mach1.start();
        //cam1.start();
        //cam1.snap();

        /*
        UPCASTING: Casting is a thing you do with variables. Once you've created an object.
                   Those objects are always going to be whatever they are to start with (Machine will be a Machine)
                   (Camera will be a Camera...etc). You can change the variables that refer to them from one type to another! */

        Machine mach2 = new Camera(); //THIS IS POLYMORPHISM. POLYMORPHISM GUARENTEES UPCASTING

        /*
        The above can also be written more suddle by Machine mach2 = cam1;
        We've taken a variable of type Camera that refers to a Camera object and you made the variable of type Machine refer to that Camera object
        We've UPCASTED cam1 to mach2. This is up because we've gone up the class heirachy because Machine is the superclass of Camera.
        */

        //((Camera)mach2).start();//Which start method will be invoked? The answer is it depends on WHAT TYPE OF OBJECT mach2 REFERS TO!!
                       // Since it's referencing a Camera object. the start method in Camera will be invoked.
                       //The methods/implementation is stored in the INSTANCE/OBJECT not THE OBJECT REFERENCE.
                       //In upcasting, you don't need Parenthesis like a regular cast. Ask about higher up superclasses.

        //((Camera)mach2).snap(); //Mach2 does not have access because there is not a snap method in the Machine class.
                      //In order for mach2 to use the snap method, we must downcast because Camera, a subclass of Machine,
                      //has a snap method which Machine does not have.


        //DOWNCASTING (you need parenthesis)
        Machine mach3 = cam1; //This works because you are giving A machine type camera behavior.

        //Camera cam2 = (Camera) mach2;
        //cam2.start();

        /*
        When you downcast, Java wants the confirmation that you know what you are doing.
        //What's the point of downcasting if mach3 has the behavior of Camera already? Is it because Camera has methods
        that Machine doesn't have and you want to access those methods as a Machine
        ***WHEN YOU DOWNCAST YOU REALLY NEED TO BE CAREFUL THAT THE VARIABLE THAT YOUR'RE DOWNCASTING REALLY REFERS TO THE OBJECT THAT YOU ARE
            DOWNCASTING TO (IN THIS CASE, mach3 does refer to Camera because of the previous line)
        */
        //cam2.start();
        //cam2.snap();

        mach3.snap(); //WHY IS THIS WRONG???


        /*Machine mach4 = new Machine();
        Camera cam3 = (Camera) mach4; //This will be a runtime error because of a ClastCastException. The actual object is a Machine and you
                                      // can't just cast the Machine object into a Camera. Objects are what they are, they don't change.
        cam3.start();
        cam3.snap();*/


    }
}
