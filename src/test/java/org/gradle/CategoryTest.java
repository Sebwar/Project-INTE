package org.gradle;

import static org.junit.Assert.*;
import org.junit.Test;

public class CategoryTest {
	
	private static final int VALID_ID = 1;
	private static final int VALID_ID2 = 1;
	private static final String VALID_NAME = "test";

	@Test
	public void testConstructorValid() {
		Category test = new Category(VALID_ID, VALID_NAME);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorIDAlreadyExists() {
		Category test = new Category(VALID_ID2, VALID_NAME);
		test.addToList();
		Category test2 = new Category(VALID_ID2, VALID_NAME);
		test2.addToList();
		
		Category.clearList();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNegativeID() {
		Category test = new Category(-1, VALID_NAME);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorNullName() {
		Category test = new Category(VALID_ID, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConstructorEmptyName() {
		Category test = new Category(VALID_ID, "");
	}
}
