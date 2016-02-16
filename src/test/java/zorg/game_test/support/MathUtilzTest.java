package zorg.game_test.support;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

public class MathUtilzTest {

	private Logger log = Logger.getGlobal();

	@Test
	public void testGreatestCommonDivisor() {
		assertEquals(1, MathUtilz.greatestCommonDivisor(1, 1));
		assertEquals(1, MathUtilz.greatestCommonDivisor(3, 2));
		assertEquals(3, MathUtilz.greatestCommonDivisor(3, 9));
		assertEquals(9, MathUtilz.greatestCommonDivisor(18, 9));
		assertEquals(16, MathUtilz.greatestCommonDivisor(48, 64));
		assertEquals(1, MathUtilz.greatestCommonDivisor(9967, 9949));
		assertEquals(9967, MathUtilz.greatestCommonDivisor(9967, 9967));
		assertEquals(1, MathUtilz.greatestCommonDivisor(1, 1, 3, 6));
		assertEquals(3, MathUtilz.greatestCommonDivisor(3, 6, 9, 12));
		assertEquals(2, MathUtilz.greatestCommonDivisor(4, 12, 14));
		assertEquals(2, MathUtilz.greatestCommonDivisor(14, 12, 4));
		assertEquals(2, MathUtilz.greatestCommonDivisor(12, 4, 14));
		assertEquals(4, MathUtilz.greatestCommonDivisor(24, 28, 8));
		assertEquals(1, MathUtilz.greatestCommonDivisor(12, 14, 4, 9949));
		assertEquals(88, MathUtilz.greatestCommonDivisor(88, 88, 88, 88, 88));
	}

	@Test
	public void testGreatestCommonDivisorResp() {
		long t0 = System.nanoTime();
		assertEquals(1, MathUtilz.greatestCommonDivisor(1, 1));
		long t1 = System.nanoTime();
		log.info("testResp WarmUp用时：" + (t1 - t0) + " nanos");

		t0 = System.nanoTime();
		assertEquals(100, MathUtilz.greatestCommonDivisor(100, 100));
		t1 = System.nanoTime();
		log.info("testResp1 用时：" + (t1 - t0) + " nanos");

		t0 = System.nanoTime();
		assertEquals(1,
				MathUtilz.greatestCommonDivisor(99490000, 9949000, 994900, 9967));
		t1 = System.nanoTime();
		log.info("testResp2 用时：" + (t1 - t0) + " nanos");

		t0 = System.nanoTime();
		assertEquals(1,
				MathUtilz.greatestCommonDivisor(9967, 99490000, 9949000, 994900));
		t1 = System.nanoTime();
		log.info("testResp3 用时：" + (t1 - t0) + " nanos");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGreatestCommonDivisorArguments() {
		MathUtilz.greatestCommonDivisor(-1, 0);
	}

	@Test
	public void testReduct() {
		int[] a = { 60, 80, 400, 100 };
		MathUtilz.reduct(a);
		assertArrayEquals(new int[] { 3, 4, 20, 5 }, a);
	}
}
