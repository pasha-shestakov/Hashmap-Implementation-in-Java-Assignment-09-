package assignment09;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChainingHashTableTest2 {

  private ChainingHashTable testChainHash;

  private ArrayList<String> testArr;

  @Before
  public void setUp() throws Exception {

    testChainHash = new ChainingHashTable(13, new MediocreHashFunctor());
    testArr = new ArrayList<>();
    testArr.add("one");
    testArr.add("two");
    testArr.add("three");
    testArr.add("four");
    testArr.add("two");
  }

  @After
  public void tearDown() throws Exception {
    testChainHash= null;
    testArr = null;
  }

  @Test
  public void testAdd_And_Duplicates() {
    Assert.assertTrue(testChainHash.add("test"));
    Assert.assertFalse(testChainHash.add("test"));
  }

  @Test
  public void testContainsSimple() {
    Assert.assertTrue(testChainHash.add("test"));
    Assert.assertTrue(testChainHash.add("HelloTesting"));
    Assert.assertTrue(testChainHash.add("moretests"));
    Assert.assertTrue(testChainHash.add("Howmanymore"));
    Assert.assertTrue(testChainHash.contains("moretests"));
  }

  @Test
  public void testsContains_NotInTable() {
    Assert.assertTrue(testChainHash.add("test"));
    Assert.assertTrue(testChainHash.add("HelloTesting"));
    Assert.assertTrue(testChainHash.add("moretests"));
    Assert.assertTrue(testChainHash.add("Howmanymore"));
    Assert.assertFalse(testChainHash.contains("notthere"));
  }

  @Test
  public void testSize_NotEmpty() {
    Assert.assertTrue(testChainHash.add("test"));
    Assert.assertTrue(testChainHash.add("HelloTesting"));
    Assert.assertTrue(testChainHash.add("moretests"));
    Assert.assertTrue(testChainHash.add("Howmanymore"));
    Assert.assertTrue(testChainHash.add("anotherone"));
    Assert.assertFalse(testChainHash.add("anotherone"));
    Assert.assertEquals(5, testChainHash.size());
    Assert.assertFalse(testChainHash.contains("notthere"));

  }

  @Test
  public void testEmpty() {
    Assert.assertEquals(0, testChainHash.size());
    Assert.assertTrue(testChainHash.isEmpty());
  }

  @Test
  public void testNotEmpty() {
    Assert.assertTrue(testChainHash.add("hereyougo"));
    Assert.assertFalse(testChainHash.isEmpty());
    Assert.assertEquals(1, testChainHash.size());
  }

  @Test
  public void testAddAll() {
    Assert.assertTrue(testChainHash.addAll(testArr));
    System.out.print(testChainHash.size());
    Assert.assertEquals(4, testChainHash.size());
    Assert.assertTrue(testChainHash.containsAll(testArr));
  }

  @Test
  public void testChainingHashTable() {
    ChainingHashTable testTable = new ChainingHashTable(7, new MediocreHashFunctor());
    Assert.assertEquals(testTable.size, 0);
    Assert.assertEquals(testTable.capacity, 7);
    Assert.assertEquals(testTable.add("One"), true);
    Assert.assertEquals(testTable.add("One"), false);
    Assert.assertEquals(testTable.add("Two"), true);
    Assert.assertEquals(testTable.add("Three"), true);
    Assert.assertEquals(testTable.add("Four"), true);
    Assert.assertEquals(testTable.add("Five"), true);
    Assert.assertEquals(testTable.add("Six"), true);
    ArrayList<String> testList = new ArrayList<>();
    testList.add("Seven");
    testList.add("Eight");
    testList.add("Nine");
    Assert.assertEquals(testTable.addAll(testList), true);
    Assert.assertEquals(testTable.addAll(testList), false);
    Assert.assertEquals(testTable.contains("One"), true);
    Assert.assertEquals(testTable.contains("Ten"), false);
    Assert.assertEquals(testTable.containsAll(testList), true);
    testList.add("Ten");
    Assert.assertEquals(testTable.containsAll(testList), false);
    testTable.clear();
    Assert.assertEquals(testTable.size(), 0);
    Assert.assertEquals(testTable.isEmpty(), true);

  }
}