public class Tester {
    /* POLYMORPHISM IN A NUTSHELL: IF YOU HAVE A SUBCLASS OF A SUPERCLASS, YOU CAN ALWAYS USE THE SUBCLASS ANYWHERE WHERE YOU MIGHT USE THE SUPERCLASS. */
    public static void main(String[] args) {

        Plant plant1 = new Plant();
        Tree tree1 = new Tree();

        Plant plant2 = tree1; //Creating another object reference pointing to the plant1 instance. Since tree is a subclass of Plant,
                              // you're guareenteed to use tree wherever you have a plant. you also say Plant plant2 = tree1;
                              //This is polymorphism at work. plant2 is a tree.
        //plant2.grow();

        tree1.shedLeaves();
        ((Tree)plant2).shedLeaves(); //Even though plant2 is a tree, This will NOT WORK BECAUSE you have to access the tree method from the Tree type
                               // because shedLeaves is not a method that is overriden from Plant? It's the type of the variable that decides what methods
                               //you can call.

       doGrow(tree1); //This works and you can pass tree1 argument as a Plant parameter in doGrow() because tree1 is an instance of Plant.
    }

    public static void doGrow(Plant plant) {
        plant.grow();
    }
}
