package assignment09;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChainingHashTableTest3 {
	
	private ChainingHashTable chainingTable;
	
	private ArrayList<String> strArr;
	
	@Before
	public void setUp() throws Exception {
		chainingTable = new ChainingHashTable(10000, new MediocreHashFunctor());
		
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
		assertTrue(chainingTable.add(item));
		assertFalse(chainingTable.add(item));
		assertEquals(1, chainingTable.size());
		assertTrue(chainingTable.contains(item));
		
		for (String str : strArr) {
			assertTrue(chainingTable.add(str));
		}
	}

	@Test
	public void testAddAll() {
		assertTrue(chainingTable.addAll(strArr));
		assertEquals(10000, chainingTable.size());
		
		assertFalse(chainingTable.addAll(new ArrayList<String>()));
	}

	@Test
	public void testClear() {
		String item = StringGenerator.generateString();
		chainingTable.addAll(strArr);
		chainingTable.add(item);
		chainingTable.clear();
		assertEquals(0, chainingTable.size());
		assertFalse(chainingTable.contains(item));
		assertTrue(chainingTable.isEmpty());
		
		for (String str : strArr) {
			assertTrue(chainingTable.add(str));
			assertTrue(chainingTable.contains(str));
		}
	}

	@Test
	public void testContains() {
		chainingTable.addAll(strArr);
		ArrayList<String> containsAllList = new ArrayList<String>();
		
		for (int index = 0; index < 599; index+=2) {
			containsAllList.add(strArr.get(index));
		}
		
		assertTrue(chainingTable.containsAll(containsAllList));
		containsAllList.add("aaaaaa");
		assertFalse(chainingTable.containsAll(containsAllList));
	}

	@Test
	public void testContainsAll() {
		chainingTable.addAll(strArr);
		assertTrue(chainingTable.contains(strArr.get(20)));
		assertTrue(chainingTable.contains(strArr.get(100)));
		
		assertTrue(chainingTable.containsAll(new ArrayList<String>()));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(chainingTable.isEmpty());
		String item = StringGenerator.generateString();
		chainingTable.add(item);
		assertFalse(chainingTable.isEmpty());
	}

	@Test
	public void testSize() {
		chainingTable.addAll(strArr);
		assertEquals(10000, chainingTable.size());
	}

}
