package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Boardgame;
import model.Case;
import model.Theme;
import test.Explorateur;

class TestBoardgame {
	private Boardgame board;
	private List<Case> cases;
	
	@BeforeEach
	void setUp() throws Exception {
		board = new Boardgame();
		Field field = board.getClass().getDeclaredField("cases");
		field.setAccessible(true);
		cases = (List<Case>)field.get(board);
	}

	@AfterEach
	void tearDown() throws Exception {
		board = null;
		cases = null;
	}

	@Test
	void testAdd() {
		//Creation of a new case
		Case case1 = new Case(Theme.IMPROBABLE);
		
		//Adding the case to the list
		assertTrue(board.add(case1), "The method returned true");
		assertEquals(1, cases.size(), "The board contains one case");
		assertTrue(cases.contains(case1), "The board contains the case one");
	}
}
