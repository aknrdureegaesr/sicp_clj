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
	public void test1() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(1, 2, 3);
		assertEquals((Integer) 13, result);
	}

	@Test
	public void test2() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(2, 3, 1);
		assertEquals((Integer) 13, result);
	}

	@Test
	public void test3() throws Exception {
		Var ex1_3 = RT.var("chapter1.Answers", "ex1_3");
		Object result = ex1_3.invoke(3, 1, 2);
		assertEquals((Integer) 13, result);
	}
}
