package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DoubleException;
import exceptions.NullException;
import model.Pawn;
import model.PawnManagement;

class TestPawnManagement {
	private PawnManagement pawnManagement;
	private List<Pawn> pawns;
	
	//Creation of pawns
	private Pawn pawn1 = new Pawn("Path1");
	private Pawn pawn2 = new Pawn("Path2");
	
	@BeforeEach
	void setUp() throws Exception {
		pawnManagement = new PawnManagement();
		//Get the attribute pawns from the pawnManagement
		Field field = pawnManagement.getClass().getDeclaredField("pawns");
		field.setAccessible(true);
		pawns = (List<Pawn>)field.get(pawnManagement);
	}

	@AfterEach
	void tearDown() throws Exception {
		pawnManagement = null;
		pawns = null;
	}

	@Test
	void testAdd() throws NullException, DoubleException {
		//Check with correct values
		pawnManagement.add(pawn1);
		assertTrue(pawns.contains(pawn1), "The pawn 1 is in the list of pawns");
		assertEquals(1, pawns.size(), "The list of pawns contains 1 pawn");
		
		pawnManagement.add(pawn2);
		assertTrue(pawns.contains(pawn2), "The pawn 2 is in the list of pawns");
		assertEquals(2, pawns.size(), "The list of pawns contains 2 pawns");
	}
	
	@Test
	void testAddDouble() {
		//Check with a double of pawn1 
		pawns.add(pawn1);
		assertThrows(DoubleException.class, ()->pawnManagement.add(pawn1), "DoubleException thrown");
		assertTrue(pawns.contains(pawn1), "The pawn 1 is still in the list");
		assertEquals(1, pawns.size(), "The list of pawns still contains 1 pawns");
	}
	
	@Test
	void testAddNull() {
		//Check with a pawn null
		assertThrows(NullException.class, ()->pawnManagement.add(null), "NullException thrown");
		assertEquals(0, pawns.size(), "The list of pawns contains no pawn");
	}

	@Test
	void testFindInt() {
		//Adding the pawns to the list
		pawns.addAll(Arrays.asList(pawn1, pawn2));
		
		//Check with correct values (Should work)
		assertEquals(pawn1, pawnManagement.find(0), "The pawn returned was the correct one");
		assertEquals(pawn2, pawnManagement.find(1), "The pawn returned was the correct one");
	}
	
	@Test
	void testFindIntIndexOutOfBounds() {
		//Adding the pawns to the list
		pawns.addAll(Arrays.asList(pawn1, pawn2));
		
		//Check with a negative index
		assertThrows(IndexOutOfBoundsException.class, ()->pawnManagement.find(-1), "IndexOutOfBoundsException thrown");
		//Check with an index too big
		assertThrows(IndexOutOfBoundsException.class, ()->pawnManagement.find(pawns.size()), "IndexOutOfBoundsException thrown");
	}
	
	@Test 
	void testFindPawn() {
		//Adding the pawns to the list
		pawns.addAll(Arrays.asList(pawn1, pawn2));
		
		//Check with correct values
		assertEquals(0, pawnManagement.find(pawn1), "The method returned 0");
		assertEquals(1, pawnManagement.find(pawn2), "The method returned 1");
		
		//Check with a pawn not in the list
		assertEquals(-1, pawnManagement.find(new Pawn("test")), "The method returned -1");
		assertEquals(-1, pawnManagement.find(null), "The method returned -1");
	}

	@Test
	void testRemoveAll() {
		//Adding the pawns to the list
		pawns.addAll(Arrays.asList(pawn1, pawn2));
		
		//Check with correct values
		assertTrue(pawnManagement.removeAll(), "The method returned true");
		assertEquals(0, pawns.size(), "The list of pawns does not contain any pawns anymore");
		
		//Check with an empty list
		assertFalse(pawnManagement.removeAll(), "The method returned false");
		assertEquals(0, pawns.size(), "The list of pawns still contains no pawn");
	}

	@Test
	void testGetNbPawns() {
		//Check with no pawn
		assertEquals(0, pawnManagement.getNbPawns(), "The method returned the correct number");
		
		//Check with one pawn
		pawns.add(pawn1);
		assertEquals(1, pawnManagement.getNbPawns(), "The method returned the correct number");
		
		//Check with a new pawn
		pawns.add(pawn2);
		assertEquals(2, pawnManagement.getNbPawns(), "The method returned the correct number");
	}
}
