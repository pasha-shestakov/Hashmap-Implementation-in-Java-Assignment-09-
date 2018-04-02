package assignment09;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QuadProbeHashTableTest {

    private QuadProbeHashTable hashTable;
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
        hashTable = new QuadProbeHashTable(13, goodFunctor);
    }

    @Test
    public void contains(){
        QuadProbeHashTable testHashTable = new QuadProbeHashTable(4, new GoodHashFunctor());
        Assert.assertTrue(testHashTable.add("test"));
        Assert.assertTrue(testHashTable.add("test2"));
        Assert.assertTrue(testHashTable.add("test3"));
        Assert.assertTrue(testHashTable.add("test4"));
        Assert.assertTrue(testHashTable.add("test5"));
        Assert.assertTrue(testHashTable.add("test6"));
        Assert.assertFalse(testHashTable.add("test6"));
        Assert.assertTrue(testHashTable.add("test7"));
        Assert.assertTrue(testHashTable.add("test8"));

        Assert.assertTrue(testHashTable.contains("test"));
        testHashTable.clear();
        Assert.assertFalse(testHashTable.contains("test"));
        Assert.assertTrue(testHashTable.add("test1"));
    }

    @Test
    public void contains2() {
        Assert.assertTrue(hashTable.add("test"));
        Assert.assertTrue(hashTable.add("HelloTesting"));
        Assert.assertTrue(hashTable.add("moretests"));
        Assert.assertTrue(hashTable.add("Howmanymore"));
        Assert.assertTrue(hashTable.contains("moretests"));
    }

    @Test
    public void add(){
        QuadProbeHashTable testHashTable = new QuadProbeHashTable(4, new GoodHashFunctor());
        Assert.assertTrue(testHashTable.add("test"));
        Assert.assertTrue(testHashTable.add(""));
        Assert.assertFalse(testHashTable.add("test"));
        Assert.assertTrue(testHashTable.contains(""));
        testHashTable.clear();
        Assert.assertFalse(testHashTable.contains("test"));
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
    public void addWithCollision(){
        QuadProbeHashTable badHashTable = new QuadProbeHashTable(5, badFunctor);
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
        list.add("hi2");
        list.add("suh");
        list.add("suh");
        Assert.assertTrue(hashTable.addAll(list));
        list.add("hi");
        Assert.assertFalse(hashTable.addAll(list));

    }
    //Test probe
    @Test
    public void addWithDupandSameHash(){
        QuadProbeHashTable badHashTable = new QuadProbeHashTable(5, badFunctor);
        badHashTable.add("hello");

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
