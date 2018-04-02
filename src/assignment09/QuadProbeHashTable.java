package assignment09;

import java.util.Collection;

/**
 * HashTable implementation using quadratic probing
 *
 * @author Pavel Shestakov
 * @version 3/30/2018
 */
public class QuadProbeHashTable implements Set<String> {

    private String[] backingArray; //Array of type Entry that is the backing structure for the hashtable.
    private HashFunctor functor; //The functor defines what hashing algorithm we use.
    public int capacity; //The size of our backing array including null elements.
    public int size; //The size of our backing array including only non null elements.
    private double loadFactor; //the ratio of how full the array is. (capacity/size)
    private int collisions;

    /**
     * Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
    public QuadProbeHashTable(int capacity, HashFunctor functor) {
        if(capacity < 0 || functor == null) throw new IllegalArgumentException();
        while (!isPrime(capacity)) {
            capacity++;
        }
        this.backingArray = new String[capacity];
        this.functor = functor;
        this.capacity = capacity;
        this.collisions = 0;
    }

    /**
     * Hashes the item and mods it to guarantee a spot in the array
     * Does not account for collision, the index returned is not guaranteed to be correct.
     * @param item The item to insert
     * @return the index where item should try to be inserted, not accounting for collision.
     */
    private int getIndex(String item) {
        int hash = functor.hash(item);
        hash = Math.abs(hash);
        return hash % this.backingArray.length;
    }

    /**
     * Doubles the size of the backing array and adds/rehashes all the elements
     * Called when load factor > .5
     */
    private void rehash() {
        this.size = 0;
        int temp = this.backingArray.length * 2;
        while (!isPrime(temp)) {
            temp++;
        }
        String[] oldBackingArray = this.backingArray;
        this.backingArray = new String[temp];
        for (String entry : oldBackingArray) {
            if (entry != null)
                add(entry);
        }
    }

    /**
     * {@link Set#add(Object)}
     */
    @Override
    public boolean add(String item) {
        if (!contains(item)) {
           // backingArray[probe(item)] = item;
            backingArray[probe(item)] = item;
       /*     int index = getIndex(item);
            if (backingArray[index] == null) { //if the spot is empty and we don't have to probe
                backingArray[index] = item;
            } else {
                backingArray[probe(item)] = item;
            }*/
            size++;
            loadFactor = (double) size / (double) this.backingArray.length;
            if (loadFactor >= .5) {
                rehash();
            }
            return true;
        }
        return false;
    }

    /**
     * {@link Set#addAll(Collection)}
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        boolean changed = false;
        for(String item : items){
            if(add(item)) changed = true;
        }
        return changed;
    }

    /**
     * {@link Set#clear()}
     */
    @Override
    public void clear() {
        this.size = 0;
        this.backingArray = new String[this.capacity];
        this.collisions = 0;
    }

    /**
     * {@link Set#contains(Object)}
     */
    @Override
    public boolean contains(String item) {
         return ( backingArray[probe(item)] != null && backingArray[probe(item)].equals(item));
    }

    /**
     * {@link Set#containsAll(Collection)}
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for (String item : items) {
            if (!contains(item)) return false;
        }
        return true;
    }

    /**
     * {@link Set#isEmpty()}
     */
    @Override
    public boolean isEmpty() {
        return this.size < 1;
    }

    /**
     * {@link Set#size()}
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Uses quadratic probing to find the index of item, or where it should go.
     * @param item The item to find the index of/ correct position
     * @return The actual exact index, accounting for collision
     */
    private int probe(String item) {
        int index = getIndex(item);
        int count = 1;
        while (backingArray[index] != null) {  //this allows us to use for two purposes. Find and open spot and look up the spot of item.
            if(this.backingArray[index].equals(item))
                return index;
            index = index + (count * count);
            index = index % this.backingArray.length;
          //  while (index >= this.backingArray.length) //reached the end of the array, wrap around;
            //    index -= this.backingArray.length;
            count++;
            this.collisions++;

        }
        return index;
    }

    private int probeAdd(String item) {
        int index = getIndex(item);
        int count = 1;
        while (backingArray[index] != null) {  //this allows us to use for two purposes. Find and open spot and look up the spot of item.
            index = index + (count * count);
            index = index % this.backingArray.length;
            count++;
        }
        return index;
    }





    /**
     * Checks if the given number is prime.
     *
     * @param num number to check if its prime
     * @return True if num is prime, false otherwise.
     */
    private static boolean isPrime(int num) {
        //see if divisable up to the square root
        if (num < 2)
            return false;
        for (int factor = 2; factor < Math.sqrt(num); factor++) {
            if (num % factor == 0)
                return false;

        }
        return true;
    }

    public int getCollisions() {
        return collisions;
    }

    public void setCollisions(int collisions) {
        this.collisions = collisions;
    }
}
