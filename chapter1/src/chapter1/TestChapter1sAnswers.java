package chapter1;

import clojure.lang.RT;
import clojure.lang.Var;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestChapter1sAnswers {
	
	@BeforeClass
	public static void load() throws Exception {
		RT.loadResourceScript("chapter1/Answers.clj");
		
	}
	
	@Test
	public void testHello() throws Exception {
		Var hello = RT.var("chapter1.Answers", "hello");
		Object result = hello.invoke("Welt");
		assertEquals("Hello Welt!", result);
	}
	
	@Test
	public void test1() throws Exception {
		Var ex1_4 = RT.var("chapter1.Answers", "ex1_4");
		Object result = ex1_4.invoke(1, 2, 3);
		assertEquals((Integer) 13, result);
	}

	@Test
	public void test2() throws Exception {
		Var ex1_4 = RT.var("chapter1.Answers", "ex1_4");
		Object result = ex1_4.invoke(2, 3, 1);
		assertEquals((Integer) 13, result);
	}

	@Test
	public void test3() throws Exception {
		Var ex1_4 = RT.var("chapter1.Answers", "ex1_4");
		Object result = ex1_4.invoke(3, 1, 2);
		assertEquals((Integer) 13, result);
	}
}
