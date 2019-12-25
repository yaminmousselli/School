/*
You do not need to import iterable, just the iterator. The iterable interface
has an iterator method. You need to use hasNext and next.

*/

import java.util.LinkedList;
import java.util.Iterator;

public class UrlLibrary implements Iterable<String> {

    private LinkedList<String> urls = new LinkedList<String>();

    private class urlIterator implements Iterator<String> {

    }
    
    public UrlLibrary() {
        urls.add("http://www.caveofprogramming.com");
        urls.add("http://www.facebook.com/caveofprogramming");
        urls.add("http://www.bbc.co.uk");
    }
    /*@Override
    public Iterator<String> iterator() {
        return urls.iterator(); //will return an iterator to the objects in the list.
    }*/


}
