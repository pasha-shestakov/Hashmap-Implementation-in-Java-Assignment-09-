package assignment09;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HashFunctorsTest {
	
	private BadHashFunctor badFunctor;
	
	private MediocreHashFunctor mediocreFunctor;
	
	private GoodHashFunctor goodFunctor;

	@Before
	public void setUp() throws Exception {
		badFunctor = new BadHashFunctor();
		mediocreFunctor = new MediocreHashFunctor();
		goodFunctor = new GoodHashFunctor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBadHashFunctor() {
		assertEquals(4, badFunctor.hash("aaaa"));
		assertEquals(0, badFunctor.hash(""));
		assertTrue(badFunctor.hash("aaaaaa") == badFunctor.hash("bbbbbb"));
		System.out.println(badFunctor.hash("wertyuiokjhgfdltyjnbvcdrtyjknbvyjkmnbhukmnhukmnhjk,mnbgjnvyhjiaghiahuwyeoieqjghjashfiqehwgou"));
	}
	
	@Test
	public void testMediocreHashFunctor() {
		assertEquals(97, mediocreFunctor.hash("a"));
		assertEquals(325, mediocreFunctor.hash("AAAAA"));
		assertTrue(mediocreFunctor.hash("ac") == mediocreFunctor.hash("bb"));
		assertEquals(0, mediocreFunctor.hash(""));
		System.out.println(mediocreFunctor.hash("wertyuijiaghiahuwyeoieqjghjashfiqehwgou"));
	}
	
	@Test
	public void testGoodHashFunctor() {
		assertTrue(goodFunctor.hash("abCDeFgH") == goodFunctor.hash("abCDeFgH"));
		assertEquals(0, goodFunctor.hash(""));
		//assertTrue(goodFunctor.hash("wertyuiokjhgfdluytrertykljhgfTKFJfhkjb274623948lkgjl;jv'ojf'a;jiaghiahuwyeoieqjghjashfiqehwgou") > 0);
		//System.out.println(goodFunctor.hash("wertyuiokjhgfdltyjnbvcdrtyjknbvyjkmnbhukmnhukmnhjk,mnbgjnvyhjiaghiahuwyeoieqjghjashfiqehwgou"));
	}

}
