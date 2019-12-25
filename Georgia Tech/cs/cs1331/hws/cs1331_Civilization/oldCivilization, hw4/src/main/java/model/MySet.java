package model;
import java.util.Random;

class MySet<E> implements SimpleSet<E> {
    private E[] mySetArray;
    private int count;
    private Random rand;

    public MySet() {
        mySetArray = (E[]) new Object[10];
        rand = new Random();
        count = 0;
//Random is better cryptographically, more secure, and therefore harder to
//crack than Math.Random().
    }

    private void reSize() {
        E[] tempArray = (E[]) new Object[mySetArray.length * 2];
        for (int i = 0; i < mySetArray.length; ++i) {
            tempArray[i] = mySetArray[i];
        }
        mySetArray = tempArray;
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        if (count == mySetArray.length) {
            reSize();
        }
        mySetArray[count] = e;
        count++;
        return true;
    }

    @Override //Eliminates duplicates, returns the duplicated element
    public E remove(E e) throws ElementDoesNotExistException {
        if (contains(e)) {
            for (int i = 0; i < mySetArray.length; ++i) {
                if (mySetArray[i].equals(e)) {
                    E toReturn = mySetArray[i];
                    mySetArray[i] = null;
                    --count;
        //Check different cases, this pushes everything after null backwards.
        //and sets last element to null
                    for (int j = i; j <= count - 1; j++) {
                        mySetArray[j] = mySetArray[j + 1];
                    }
                    mySetArray[count] = null;
                    return toReturn;
                }
            }
        }
        throw new ElementDoesNotExistException("Element does not exist");
    }

    @Override
    public boolean contains(E e) {
        for (int i = 0; i < count; ++i) {
            //YOU MUST CHECK THAT BOTH ARE NULL, NOT JUST THE INDEX!!!
            if (mySetArray[i] == null || e == null) {
                if (mySetArray[i] == e) {
                    return true;
                }
            } else {
                if (mySetArray[i].equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E[] removeAll(E[] e) throws ElementDoesNotExistException {
        for (int i = 0; i < e.length; ++i) {
            if (!contains(e[i])) {
                throw new ElementDoesNotExistException("Element is missing");
            }
        }
        E[] removedElements = (E[]) new Object[e.length];
        for (int i = 0; i < e.length; ++i) {
            try {
                removedElements[i] = remove(e[i]);
            } catch (ElementDoesNotExistException duplicate) {
                System.out.println("Already removed element that exists");
            }
        }
        return removedElements;
    }

    @Override
    public void clear() {
        mySetArray = (E[]) new Object[10];
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return !(count >= 1);
    }

    @Override
    public E getRandomElement() throws ElementDoesNotExistException {
        if (count == 0) {
            throw new ElementDoesNotExistException("Set is empty");
        }
        int randNums = rand.nextInt(count);
        E returnRandE = mySetArray[randNums];
        return returnRandE;
    }

    @Override
    public E[] toArray() {
        E[] notNullArray = (E[]) new Object[count];
        for (int i = 0; i < count; ++i) {
            notNullArray[i] = mySetArray[i];
        }
        return notNullArray;
    }

    @Override
    public String toString() {
        String printSetE = "";
        for (int i = 0; i < count; i++) {
            if (mySetArray[i] == null) {
                printSetE += " null,";
            } else {
                //you technically don't need toString()? Just incase though
                printSetE += mySetArray[i].toString() + ", ";
            }
        }
        return printSetE;
    }
}
