package assignment09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChainingHashTableTest {

    private ChainingHashTable hashTable;
    private HashFunctor goodFunctor;
    private HashFunctor badFunctor;
    @Before
    public void setUp(){
        badFunctor = new HashFunctor() {
            @Override
            public int hash(String item) {
                return 0;
            }
        };


        goodFunctor = new HashFunctor() {
            @Override
            public int hash(String item) {
                return item.hashCode();
            }
        };
        hashTable = new ChainingHashTable(13, goodFunctor);
    }

    @Test
    public void addAndContains(){
        for(int i = 0; i < 5000; i++){
            hashTable.add("" + i);
        }
        Assert.assertTrue(hashTable.contains("450"));
        Assert.assertFalse(hashTable.contains("5000"));
    }

    @Test
    public void contains(){
        ChainingHashTable testHashTable = new ChainingHashTable(4, new GoodHashFunctor());
        Assert.assertTrue(testHashTable.add("test"));
        Assert.assertTrue(testHashTable.add("test2"));
        Assert.assertTrue(testHashTable.add("test3"));
        Assert.assertFalse(testHashTable.add("test3"));
        Assert.assertTrue(testHashTable.add("test4"));
        Assert.assertTrue(testHashTable.add("test5"));
        Assert.assertTrue(testHashTable.add("test6"));
        Assert.assertFalse(testHashTable.add("test6"));
        Assert.assertFalse(testHashTable.add("test6"));
        Assert.assertFalse(testHashTable.add("test6"));
        Assert.assertTrue(testHashTable.add("test7"));
        Assert.assertTrue(testHashTable.add("test8"));

        Assert.assertTrue(testHashTable.contains("test"));

    }
    @Test
    public void add(){
        ChainingHashTable testHashTable = new ChainingHashTable(4, new GoodHashFunctor());
        Assert.assertTrue(testHashTable.add("test"));
        Assert.assertFalse(testHashTable.add("test"));
        testHashTable.clear();
        Assert.assertFalse(testHashTable.contains("test"));
    }

    @Test
    public void addWithCollision(){
        ChainingHashTable badHashTable = new ChainingHashTable(5, badFunctor);
        badHashTable.add("Suh");
        badHashTable.add("yo");
        badHashTable.add("yo1");
        badHashTable.add("yo2");
        badHashTable.add("yo3");
        Assert.assertTrue(badHashTable.contains("yo"));
    }
    @Test
    public void addAll() {
        List<String> list = new ArrayList<>();
        list.add("hi");
        list.add("suh");
        Assert.assertTrue(hashTable.addAll(list));
        list.add("hi");
        Assert.assertFalse(hashTable.addAll(list));

    }

    @Test
    public void clearAndSize() {
        hashTable.add("hey");
        hashTable.add("hey2");
        hashTable.add("hey3");
        Assert.assertTrue(hashTable.size() == 3);
        hashTable.clear();
        Assert.assertTrue(hashTable.size() == 0);
    }

    @Test
    public void containsAll() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < 5000; i++){
            list.add("" + i);
            hashTable.add("" + i);
        }
        Assert.assertTrue(hashTable.containsAll(list));
        list.add("suh");
        Assert.assertFalse(hashTable.containsAll(list));
    }

    @Test
    public void isEmpty() {
        hashTable.add("1");
        Assert.assertFalse(hashTable.isEmpty());
        hashTable.clear();
        Assert.assertTrue(hashTable.isEmpty());
    }




}
