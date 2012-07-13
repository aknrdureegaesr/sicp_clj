package chapter1.sqrt;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import clojure.lang.RT;
import clojure.lang.Var;

/**
 * Test the precision of a sqrt implementation
 * with samples from the entire range of positive double precision numbers.
 */
@RunWith(Parameterized.class)
public class SqrtPrecisionTests {

	@Parameters
	public static List<Double[]> examplesToCalculateSQRTof() {
		LinkedList<Double []> result = new LinkedList<Double[]>();
		result.add(new Double[] {1.0} );
		for (double x = Double.MIN_NORMAL; x <= Double.MAX_VALUE / Math.PI; x *= Math.PI) {
			result.add(new Double[] {x} );
		}
		return result;
	}

	final private double x;
	private double sqrt;

	public SqrtPrecisionTests(Double x) {
		this.x = x;		
	}
	
	private static Var mySqrt;

	@BeforeClass
	public static void load() throws Exception {
		RT.loadResourceScript("chapter1/sqrt/ReallyGoodSQRT.clj");
		mySqrt = RT.var("chapter1.BonusMaterial", "mysqrt");
	}

	@Before
	public void calculate() {
		this.sqrt = (Double) mySqrt.invoke(x); // Math.sqrt(x);
	}

	@Test
	public void testPrecision() {
		double square = sqrt * sqrt;

		// Allow the square to be up to two steps remote of x:
		double upperBound = Math.nextAfter(Math.nextAfter(x, Double.MAX_VALUE), Double.MAX_VALUE);
		double lowerBound = Math.nextAfter(Math.nextAfter(x, 0), 0);

//      As the derivative of x*x is 2*x, one cannot really hope
//      that this comes out true:
//		double upperBound = Math.nextAfter(x, Double.MAX_VALUE);
//		double lowerBound = Math.nextAfter(x, 0);
//      But it does in almost all cases (a mere 74 of 1239 fail).
				
		if( square < lowerBound)
			fail("calculated sqrt value " + sqrt + " for " + x + " is too low, try " + Math.sqrt(x));				
		else if(upperBound < square)
			fail("calculated sqrt value " + sqrt + " for " + x + " is too high, try " + Math.sqrt(x));
	}
}
