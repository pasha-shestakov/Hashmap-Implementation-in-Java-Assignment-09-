package assignment09;


import java.util.Collection;
import java.util.LinkedList;


/**
 * HashTable implementation using separate chaining
 *
 * @author Pavel Shestakov
 * @version 3/30/2018
 */
public class ChainingHashTable implements Set<String> {
    private HashFunctor functor; //The functor defines what hashing algorithm we use.
    public int capacity; //The size of our backing array including null elements.
    public int size; //The size of our backing array including only non null elements.
    private double loadFactor; //the ratio of how full the array is. (capacity/size)
    private int collision;
    private LinkedList<String>[] storage; //Array of linked list. Linked lists represent 'buckets' where values with duplicate hashes will be stored.

    /**
     * Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){
        if(capacity < 0 || functor == null) throw new IllegalArgumentException();
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        this.functor = functor;
        this.capacity = capacity;

        this.size = 0;
    }

    /**
     * Hashes the item and mods it to guarantee a spot in the array
     *
     * @param item The item to insert
     * @return the index where item should try to be inserted
     */
    private int getIndex(String item) {
        int hash = functor.hash(item);
        hash = Math.abs(hash);
        return hash % this.storage.length;
    }

    /**
     * {@link Set#add(Object)}
     */
    @Override
    public boolean add(String item) {
        int index = getIndex(item);
        if(contains(item)) return false;
        if(storage[index] == null) {
            storage[index] = new LinkedList<String>();
            storage[index].add(item);
        }else{
            storage[index].add(item);
            this.collision += storage[index].size();
        }
        this.size++;
        return true;

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
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        this.size = 0;
    }

    /**
     * {@link Set#contains(Object)}
     */
    @Override
    public boolean contains(String item) {
        if(storage[getIndex(item)] == null) return false; //if the linked list is null, means nothing is there so contains is false
        for(String value : storage[getIndex(item)]){
            this.collision++;
            if(value != null && item.equals(value)) return true;
        }
        return false;
    }
    /**
     * {@link Set#containsAll(Collection)}
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        for(String item : items){
            if(!contains(item)) return  false;
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

    public int getCollision() {
        return collision;
    }

    public void setCollision(int collision) {
        this.collision = collision;
    }
}
