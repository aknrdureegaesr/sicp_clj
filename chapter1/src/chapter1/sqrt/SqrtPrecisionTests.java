package chapter1.sqrt;

import static org.junit.Assert.assertTrue;

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

	/**
	 * The smallest positive number that, when added to ((double) 1.0), changes the value.
	 * <p>It is a most curious omission that {@link Double} does not have this number.</p>
	 */
    final static double SMALLEST_NUMBER_ADDABLE_TO_1 = smallestNumberAddableToOne();

    /** Allow just one bit of deviation to the low side. */
    final static double ALLOWABLE_RANGE_FACTOR_LOW = 1.0 - 0.5 * SMALLEST_NUMBER_ADDABLE_TO_1;

    /** Allow just one bit of deviation to the high side. */
    final static double ALLOWABLE_RANGE_FACTOR_HIGH = 1.0 + SMALLEST_NUMBER_ADDABLE_TO_1;

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
		// RT.loadResourceScript("chapter1/sqrt/ReallyGoodSQRT.clj");
		// mySqrt = RT.var("chapter1.BonusMaterial", "mysqrt");
	}

	@Before
	public void calculate() {
		this.sqrt = (Double) mySqrt.invoke(x); // Math.sqrt(x);
	}

	@Test
	public void testPrecision() {
		double square = sqrt * sqrt;
		assertTrue("calculated sqrt value is too low", ALLOWABLE_RANGE_FACTOR_LOW * x <= square);
		assertTrue("calculated sqrt value is too high", square <= ALLOWABLE_RANGE_FACTOR_HIGH * x);
	}

	private static double smallestNumberAddableToOne() {
		double lower = 0.0;
		double upper = 1.0;
		double candidate = 1e-10;
		final double one = 1.0;
		do {
			if(one < one + candidate)
				upper = candidate;
			else
				lower = candidate;
			candidate = (lower + upper) / 2;
		} while(lower < candidate && candidate < upper);
		return upper;
	}
}
