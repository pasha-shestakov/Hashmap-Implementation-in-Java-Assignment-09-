package assignment09;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuadProbeHashTableTest3 {
	
	private QuadProbeHashTable quadProbeTable;
	
	private ArrayList<String> strArr;
	
	@Before
	public void setUp() throws Exception {
		quadProbeTable = new QuadProbeHashTable(23, new MediocreHashFunctor());
		
		strArr = new ArrayList<String>();
		
		// Build a collection of string
		while(strArr.size() != 10000) {
			String item = StringGenerator.generateString();
			if (!strArr.contains(item)) {
				strArr.add(item);
			}
		}		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		String item = StringGenerator.generateString();
		assertTrue(quadProbeTable.add(item));
		assertFalse(quadProbeTable.add(item));
		assertEquals(1, quadProbeTable.size());
		assertTrue(quadProbeTable.contains(item));
		
		for (String str : strArr) {
			assertTrue(quadProbeTable.add(str));
			assertTrue(quadProbeTable.contains(str));
		}
	}


	@Test
	public void testAddAll() {
		assertTrue(quadProbeTable.addAll(strArr));
		assertEquals(10000, quadProbeTable.size());
		//System.out.println(quadProbeTable.countCollisions());
		
		assertFalse(quadProbeTable.addAll(new ArrayList<String>()));
	}

	@Test
	public void testClear() {
		String item = StringGenerator.generateString();
		quadProbeTable.addAll(strArr);
		quadProbeTable.add(item);
		quadProbeTable.clear();
		assertEquals(0, quadProbeTable.size());
		assertFalse(quadProbeTable.contains(item));
		quadProbeTable.add(item);
		assertEquals(1, quadProbeTable.size());
		
		for (String str : strArr) {
			assertTrue(quadProbeTable.add(str));
			assertTrue(quadProbeTable.contains(str));
		}
	}

	@Test
	public void testContains() {
		quadProbeTable.addAll(strArr);
		assertTrue(quadProbeTable.contains(strArr.get(20)));
		assertTrue(quadProbeTable.contains(strArr.get(100)));
	}

	@Test
	public void testContainsAll() {
		quadProbeTable.addAll(strArr);
		ArrayList<String> containsAllList = new ArrayList<String>();
		
		for (int index = 0; index < 599; index+=2) {
			containsAllList.add(strArr.get(index));
		}
		
		assertTrue(quadProbeTable.containsAll(containsAllList));
		containsAllList.add("aaaaaa");
		assertFalse(quadProbeTable.containsAll(containsAllList));
		
		assertTrue(quadProbeTable.containsAll(new ArrayList<String>()));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(quadProbeTable.isEmpty());
		String item = StringGenerator.generateString();
		quadProbeTable.add(item);
		assertFalse(quadProbeTable.isEmpty());
	}

	@Test
	public void testSize() {
		quadProbeTable.addAll(strArr);
		assertEquals(10000, quadProbeTable.size());
	}

}
