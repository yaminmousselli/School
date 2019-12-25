/*
1) <?> is a wild card, and you read this as ArrayList of unknown type.
This means you can pass any kind of parameter type through this method.
In our context, we can pass an ArrayList of any type paramter.

2) The disadvantage is that you can only treat the items in the list of
type Object because Object is the ultimate parent of all classes. Therefore,
you CAN'T call any Camera || Machine specific methods. Without casting,
the original type is Object when using a wildcard.

3) HOWEVER, you can use specific camera || machine specific methods, if you
know the object that you are passing in a specific list type, then you
can DOWNCAST the Object to whatever list type, and then use whatever type----->???
specific method(s) you want to use.

4) You can specify an upperbound to the wildcard. Say you want to pass
an ArrayList<Machine> || an ArrayList of any subtype of Machine. It won't
necessarily be a machine but if not, then it HAS TO BE a subtype of it.
This is when you would use an upperbound and the SYNTAX for that is
<? extends Machine>

5) Like Upperbounds, you can specify a lowerbound (less useful). The
SYNTAX for it is <? Machine super Camera>. Whatever you pass into this
method has to be Camera || a superclass of Camera. The trouble is that
a superclass of Camera may not have Camera's methods. You must treat
them as Objects.

/* COVARIANTS DO NOT APPLY TO TYPE PARAMETERS SO YOU CAN NOT PASS
CAMERA INTO THE SHOWLIST() WITH TYPE MACHINE PARAMETER.
THE SIMPLEST WAY TO DO SO IS by USING A WILDCARD, <?>

*/
import java.util.ArrayList;
class Tester {

    public static void main(String[] args) {

        ArrayList<Machine> list1 = new ArrayList<Machine>();
        list1.add(new Machine());
        list1.add(new Machine());

        ArrayList<Camera> list2 = new ArrayList<Camera>();
        list2.add(new Camera());
        list2.add(new Camera());


        showList(list1);
        showList(list2);

        showList1(list1);
        showList1(list2);

        showList2(list1);
        showList2(list2);
    }

    public static void showList(ArrayList<?>list) {
        for (Object value : list) { //Must Use Object, but can you downcast?
            System.out.println(value);
        }
    }

    public static void showList1(ArrayList<? extends Machine> list) {

        for (Object value : list) {
            System.out.println(value);
        }
        System.out.println();

        for (Machine value1 : list) {
            System.out.println(value1);
            value1.start();
        }

        System.out.println(list.get(1));
    }

    public static void showList2(ArrayList<? super Camera> list) {

        for (Object value : list) { //Must Use Object
            System.out.println(value); //If you know your specific arrayList,
            //then you can downcast? How would you go about doing this????
            //You can use instance of or getClass() to establish this????
        }
    }
}
