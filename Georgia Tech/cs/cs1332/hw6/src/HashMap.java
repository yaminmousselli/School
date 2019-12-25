import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Your implementation of HashMap.
 *
 * @author Yamin Mousselli
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     * <p>
     * Do not use magic numbers!
     * <p>
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     * <p>
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {
        double loadFactor = ((double) (size + 1)) / table.length;
        int length = ((table.length * 2) + 1);
        if (key == null) {
            throw new IllegalArgumentException("You can not insert a null key"
                    + " into this hash table");
        }
        if (value == null) {
            throw new IllegalArgumentException("You can not insert a null "
                    + "value into this hash map");
        }
        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable(length);
        }
        return putHelper(key, value, table);
    }

    /**
     * This method handles all the logic for the probing for my put method
     * and also handles the logic for my resize.
     * @param key the key to search for, compare, and insert
     * @param value the value to be stored for the particular key.
     * @param backingArray the hash map that is backed by an array to be
     * traversed
     * @return the value that is returned either by the one we overwrite or
     * null if there wasn't a key.
     */
    private V putHelper(K key, V value, MapEntry<K, V>[] backingArray) {
        V toBeReturned = null;
        int length = ((table.length * 2) + 1);

        //THIS FLAG MEANS YOU HAVEN'T FOUND A DEL FLAG. IF YOU HAVE,
        // YOU'RE REPLACING THIS VALUE WITH THE INDEX OF THE DEL FLAg
        int removedFlag = -1;
        int hash = (Math.abs(key.hashCode()) % backingArray.length);
        for (int i = 0; i < backingArray.length; i++) {
            int hashedIndex =  (hash + (i * i)) % backingArray.length;
            
            if (backingArray[hashedIndex] == null && removedFlag == -1) {
                backingArray[hashedIndex] = new MapEntry<>(key, value);
                size++;
                toBeReturned = null;
                return toBeReturned;
            } else if (backingArray[hashedIndex] == null && removedFlag != -1) {
                //THIS IS GOING BACK TO THE INDEX OF WHERE THE DEL FLAG IS
                backingArray[removedFlag].setKey(key);
                backingArray[removedFlag].setValue(value);
                backingArray[removedFlag].setRemoved(false);
                size++;
                toBeReturned = null;
                return toBeReturned;
            } else {
                if (backingArray[hashedIndex].getKey().equals(key)
                        && !(backingArray[hashedIndex].isRemoved())) {
                    toBeReturned = backingArray[hashedIndex].getValue();
                    backingArray[hashedIndex].setValue(value);
                    return toBeReturned;
                }

                if (backingArray[hashedIndex].isRemoved()
                        && removedFlag == -1) {
                    //YOU HAVE FOUND A DEL FLAG AND YOU MUST SET THE
                    // INDEX OF THAT DEL FLAG SO YOU CAN COME BACK TO IT
                    // ONCE YOU FINISH PROBING YOUR ARRAY IN THE CASE YOU
                    // DO NOT FIND A DUPLICATE KEY.
                    removedFlag = hashedIndex;
                }

                if (i == (backingArray.length - 1) && removedFlag == -1) {
                    //YOU HAVEN'T FOUND ANY DEL FLAGS AND YOU HAVE
                    // EXHAUSTED THE LENGTH OF YOUR TABLE WHILE PROBING
                    // SO YOU NEED TO RESIZE AND KEEP PROBING
                    resizeBackingTable(length);
                    return putHelper(key, value, table);
                } else if (i == (backingArray.length - 1)
                        && removedFlag != -1) {
                    //THIS CHECK IS WHEN YOU HAVE FINISHED PROBING AND
                    // YOU HAVE FOUND A DEL TAG, THEN YOU WANT TO GO BACK
                    // TO THE DEL FLAG.
                    backingArray[removedFlag].setKey(key);
                    backingArray[removedFlag].setValue(value);
                    backingArray[hashedIndex].setRemoved(false);
                    size++;
                    return toBeReturned;
                }
            }
        }
        return toBeReturned;
    }

    @Override
    public V remove(K key) {
        V removedValue = null;
        if (key == null) {
            throw new IllegalArgumentException("There aren't any null keys in"
                    + " this hash table and thus, you can not remove a null "
                    + "key");
        }
        int hash = (Math.abs(key.hashCode()) % table.length);
        for (int i = 0; i < table.length; i++) {
            int hashedIndex = (hash + (i * i)) % table.length;
            if (table[hashedIndex] != null
                    && !(table[hashedIndex].isRemoved())) {
                if (table[hashedIndex].getKey().equals(key)) {
                    removedValue = table[hashedIndex].getValue();
                    table[hashedIndex].setRemoved(true);
                    size--;
                    return removedValue;
                }
            }
            if (table[hashedIndex] == null) {
                throw new
                        NoSuchElementException("They value of the key you are "
                        + "trying to remove can not be executed because the "
                        + "key is " + "not in this table!");
            }
        }
        throw new NoSuchElementException("They value of the key you are "
                + "trying to remove can not be executed because the key is "
                + "not in this table!");
    }

    @Override
    public V get(K key) {
        V toBeReturned = null;
        if (key == null) {
            throw new IllegalArgumentException("There aren't any null keys in"
                    + " this hash table and thus, you can not retrieve "
                    + "anything");
        }
        for (int i = 0; i < table.length; i++) {
            int hashedIndex = ((Math.abs(key.hashCode()) + (i * i))
                    % table.length);
            if (table[hashedIndex] == null) {
                throw new NoSuchElementException("They value you are looking "
                        + "for does not exist");
            }
            if (table[hashedIndex] != null
                    && !(table[hashedIndex].isRemoved())) {
                if (table[hashedIndex].getKey().equals(key)) {
                    toBeReturned = table[hashedIndex].getValue();
                    return toBeReturned;
                }
            }
            if (table[hashedIndex] == null) {
                throw new
                        NoSuchElementException("They value of the key you are "
                        + "trying to retrieve is not available because the "
                        + "key is not in this table!");
            }
        }
        return toBeReturned;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("This hash table does not have"
                    + " any null keys and so it does not contain what you are"
                    + " looking for");
        }
        for (int i = 0; i < table.length; i++) {
            int hashedIndex = (Math.abs(key.hashCode()) + (i * i))
                    % table.length;
            if (table[hashedIndex] != null
                    && !(table[hashedIndex].isRemoved())) {
                if (table[hashedIndex].getKey().equals(key)) {
                    return true;
                }
            }
            if (table[hashedIndex] == null) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> toBeReturned = new HashSet<K>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                toBeReturned.add(table[i].getKey());
            }
        }
        return toBeReturned;
    }


    @Override
    public List<V> values() {
        List<V> toBeReturned = new ArrayList<V>(size);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                toBeReturned.add(table[i].getValue());
            }
        }
        return toBeReturned;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("You can not resize because "
                    + "your length is not positive");
        }
        if (length < size) {
            throw new IllegalArgumentException("You can not resize because "
                    + "your length is less than the size of elements that you"
                    + " have in your table, and thus you can not fit all of "
                    + "the necessary items that must be stored in this Hash "
                    + "Table");
        }
        size = 0;
        MapEntry<K, V>[] tempTable = (MapEntry<K, V>[]) new MapEntry[length];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !(table[i].isRemoved())) {
                putHelper(table[i].getKey(), table[i].getValue(), tempTable);
            }
        }
        table = tempTable;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }
}
