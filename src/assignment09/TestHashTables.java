package assignment09;
import java.util.ArrayList;

import junit.framework.TestCase;

public class TestHashTables extends TestCase
{
    QuadProbeHashTable testQuad;
    QuadProbeHashTable testQuadMediocre;
    QuadProbeHashTable testQuadBad;
    HashFunctor goodHashFunctor;
    ArrayList<String> test;
    ChainingHashTable testChain;
    HashFunctor badHashFunctor;
    HashFunctor mediocreHashFunctor;

    protected void setUp() throws Exception {
        super.setUp();
        test = new ArrayList<String>();
        goodHashFunctor = new GoodHashFunctor();
        mediocreHashFunctor = new MediocreHashFunctor();
        badHashFunctor = new BadHashFunctor();
        testQuadMediocre = new QuadProbeHashTable(5, mediocreHashFunctor);
        testQuadBad = new QuadProbeHashTable(5, badHashFunctor);
        testQuad = new QuadProbeHashTable(21, goodHashFunctor);
        testChain = new ChainingHashTable(21, goodHashFunctor);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddQuadGood() {
        testQuad = new QuadProbeHashTable(3, goodHashFunctor);
        assertTrue(testQuad.add("fu"));
        assertTrue(testQuad.add("fcu"));
        assertTrue(testQuad.add("zzz"));
        assertTrue(testQuad.add("fu3"));
        assertTrue(testQuad.add("fdcu"));
        assertFalse(testQuad.add("fu3"));
        assertFalse(testQuad.add("fdcu"));
    }

    public void testContainsQuadGood() {
        assertTrue(testQuad.add("fu"));
        assertTrue(testQuad.add("fcu"));
        assertTrue(testQuad.add("zzz"));
        assertTrue(testQuad.add("fu3"));
        assertTrue(testQuad.add("fdcu"));
        assertTrue(testQuad.contains("fu"));
        assertTrue(testQuad.contains("fcu"));
        assertTrue(testQuad.contains("zzz"));
        assertTrue(testQuad.contains("fu3"));
        assertTrue(testQuad.contains("fdcu"));
    }

    public void testClear() {
        assertTrue(testQuad.add("fu"));
        assertTrue(testQuad.add("fcu"));
        assertTrue(testQuad.add("zzz"));
        assertTrue(testQuad.add("fu3"));
        assertTrue(testQuad.add("fdcu"));
        testQuad.clear();
        assertEquals(0, testQuad.size());
    }

    public void testAddAllQuad() {
        test.add("Hello");
        test.add("Goodbye");
        test.add("Adios");
        assertTrue(testQuad.addAll(test));
        assertFalse(testQuad.addAll(test));
    }

    public void testContainsAllQuad() {
        test.add("Hello");
        test.add("Goodbye");
        test.add("Adios");
        ArrayList<String> falseTest = new ArrayList<String>();
        falseTest.add("Elephante");
        assertTrue(testQuad.addAll(test));
        assertTrue(testQuad.containsAll(test));
        assertFalse(testQuad.containsAll(falseTest));
    }

    // chain

    public void testAddChainGood() {
        testQuad = new QuadProbeHashTable(3, goodHashFunctor);
        assertTrue(testChain.add("fu"));
        assertTrue(testChain.add("fcu"));
        assertTrue(testChain.add("zzz"));
        assertTrue(testChain.add("fu3"));
        assertTrue(testChain.add("fdcu"));
        assertFalse(testChain.add("fu3"));
        assertFalse(testChain.add("fdcu"));
    }

    public void testContainsChainGood() {
        assertTrue(testChain.add("fu"));
        assertTrue(testChain.add("fcu"));
        assertTrue(testChain.add("zzz"));
        assertTrue(testChain.add("fu3"));
        assertTrue(testChain.add("fdcu"));
        assertTrue(testChain.contains("fu"));
        assertTrue(testChain.contains("fcu"));
        assertTrue(testChain.contains("zzz"));
        assertTrue(testChain.contains("fu3"));
        assertTrue(testChain.contains("fdcu"));
    }

    public void testChainClear() {
        assertTrue(testChain.add("fu"));
        assertTrue(testChain.add("fcu"));
        assertTrue(testChain.add("zzz"));
        assertTrue(testChain.add("fu3"));
        assertTrue(testChain.add("fdcu"));
        testChain.clear();
        assertEquals(0, testChain.size());
    }

    public void testAddAllChain() {
        test.add("Hello");
        test.add("Goodbye");
        test.add("Adios");
        assertTrue(testChain.addAll(test));
        assertFalse(testChain.addAll(test));
    }

    public void testContainsAllChain() {
        test.add("Hello");
        test.add("Goodbye");
        test.add("Adios");
        ArrayList<String> falseTest = new ArrayList<String>();
        falseTest.add("Elephante");
        assertTrue(testChain.addAll(test));
        assertTrue(testChain.containsAll(test));
        assertFalse(testChain.containsAll(falseTest));
    }

    // med. bad
    public void testBadFunctor() {
        testQuadBad = new QuadProbeHashTable(5, badHashFunctor);
        assertTrue(testQuadBad.add("Cool"));
        assertFalse(testQuadBad.add("Cool"));
        assertTrue(testQuadBad.contains("Cool"));
        testQuadBad.clear();
        assertFalse(testQuadBad.contains("Cool"));
    }

    // med. bad
    public void testOKFunctor() {
        testQuadMediocre = new QuadProbeHashTable(5, mediocreHashFunctor);
        assertTrue(testQuadMediocre.add("Cool"));
        assertFalse(testQuadMediocre.add("Cool"));
        assertTrue(testQuadMediocre.contains("Cool"));
        testQuadMediocre.clear();
        assertFalse(testQuadMediocre.contains("Cool"));
    }

}