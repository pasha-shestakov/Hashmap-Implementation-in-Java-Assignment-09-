package assignment09;

import org.junit.*;
import java.util.ArrayList;
import java.util.Random;

public class QuadProbeHashTableTest2 {

  QuadProbeHashTable testHashTable;
  ArrayList<String> testList;

  @Before
  public void setUp() {
    testHashTable = new QuadProbeHashTable(13, new BadHashFunctor());
    testList = new ArrayList<>();
    testList.add("First");
    testList.add("Second");
    testList.add("Third");
    testList.add("Fourth");
    testList.add("Fifth");
    testList.add("Sixth");
    testList.add("Seventh");
    testList.add("Eighth");
    testList.add("Ninth");
    testList.add("Tenth");
  }

  @After
  public void tearDown() {
    testHashTable = null;

  }

  @Test
  public void testQuadProbeHashTable_Add_SingleItem() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());
    testHashTable.add("test");
  }

  @Test
  public void testQuadProbeHashTable_Clear_Simple() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());
    testHashTable.add("test");
    Assert.assertFalse(testHashTable.isEmpty());
    Assert.assertEquals(1, testHashTable.size());
    testHashTable.clear();
    Assert.assertTrue(testHashTable.isEmpty());
    Assert.assertEquals(0, testHashTable.size());
  }

  @Test
  public void testQuadProbeHashTable_Add_MultipleItems() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());
    testHashTable.add("this");
    testHashTable.add("is");
    testHashTable.add("a");
    testHashTable.add("test");
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertFalse(testHashTable.isEmpty());
    Assert.assertEquals(4, testHashTable.size());
  }

  @Test
  public void testQuadProbeHashTable_Add_MultipleItemsSameLength() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());
    testHashTable.add("but");
    testHashTable.add("the");
    testHashTable.add("dog");
    testHashTable.add("was");
    testHashTable.add("old");
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertFalse(testHashTable.isEmpty());
    Assert.assertEquals(5, testHashTable.size());
    testHashTable.clear();
    Assert.assertTrue(testHashTable.isEmpty());
    Assert.assertEquals(0, testHashTable.size());
  }

  /**
   * Generates random alphanumeric String
   *
   * @param length - length of String to generate
   * @return - randomly-generated alphanumeric String
   */
  public static String generateRandomString(int length) {

    StringBuilder generatedString = new StringBuilder();
    Random r = new Random();

    for (int i = 0; i < length; ++i) {
      generatedString.append((char) ('a' + (r.nextInt(26))));
    }
    return generatedString.toString();
  }
  @Test
  public void testQuadProbeHashTable_Add_CapacityItems() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());

    for(int i = 0; i <= 13; ++i) {
      testHashTable.add(generateRandomString(i));
    }

    Assert.assertFalse(testHashTable.isEmpty());
    Assert.assertEquals(14, testHashTable.size());
    testHashTable.clear();
    Assert.assertTrue(testHashTable.isEmpty());
    Assert.assertEquals(0, testHashTable.size());
  }

  @Test
  public void testQuadProbeHashTable_AddAll() {
    Assert.assertEquals(13, testHashTable.capacity);
    Assert.assertTrue(testHashTable.isEmpty());

    Assert.assertTrue(testHashTable.addAll(testList));
    Assert.assertTrue(testHashTable.containsAll(testList));

    testHashTable.clear();
    Assert.assertTrue(testHashTable.isEmpty());
    Assert.assertEquals(0, testHashTable.size());

  }

}
