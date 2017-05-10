package org.adani.hangman.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ObjTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Obj o = new Obj(1, "String");
		Obj n = new Obj(1, "String");
		
		assertTrue(o.equals(n));
	}

}
