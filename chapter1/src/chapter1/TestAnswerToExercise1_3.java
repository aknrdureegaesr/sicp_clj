package chapter1;

import clojure.lang.RT;
import clojure.lang.Var;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestAnswerToExercise1_3 {
	
	@BeforeClass
	public static void load() throws Exception {
		RT.loadResourceScript("chapter1/AnswerToExercise_1_3.clj");
	}

	@Test
	public void test123() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(1, 2, 3);
		assertEquals(13L, result);
	}

	@Test
	public void test132() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(1, 3, 2);
		assertEquals(13L, result);
	} 

	@Test
	public void test213() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(2, 1, 3);
		assertEquals(13L, result);
	}

	@Test
	public void test231() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(2, 3, 1);
		assertEquals(13L, result);
	}

	@Test
	public void test312() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(3, 1, 2);
		assertEquals(13L, result);
	}


	@Test
	public void test321() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(3, 2, 1);
		assertEquals(13L, result);
	}

	@Test
	public void test577() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(5, 7, 7);
		assertEquals(98L, result);
	}

	@Test
	public void test757() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(7, 5, 7);
		assertEquals(98L, result);
	}

	@Test
	public void test775() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(7, 7, 5);
		assertEquals(98L, result);
	}

	@Test
	public void test922() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(9, 2, 2);
		assertEquals(85L, result);
	}

	@Test
	public void test292() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(2, 9, 2);
		assertEquals(85L, result);
	}

	@Test
	public void test229() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(2, 2, 9);
		assertEquals(85L, result);
	}

	@Test
	public void test888() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(8, 8, 8);
		assertEquals(128L, result);
	}
}
