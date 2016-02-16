package zorg.game_test.cycle;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ABCCCTest {

	@Test
	public void test1() {
		int[] c = { 1 };
		int n = 12;
		int b = 0;
		boolean[] result = ABCCC.cycleAlgorithm(c, b, n);
		boolean[] expected = { true, true, true, true, true, true, true, true,
				true, true, true, true };
		Assert.assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void test2() {
		int[] c = { 10 };
		int n = 10;
		int b = 2;
		boolean[] result = ABCCC.cycleAlgorithm(c, b, n);
		boolean[] expected = { false, false, false, false, false, false, false,
				true, false, false };
		Assert.assertTrue(Arrays.equals(result, expected));
	}

	@Test
	public void test3() {
		int[] c = { 1, 2, 1, 5, 10 };
		int n = 20;
		int b = 0;
		boolean[] expecteds = { true, false, true, true, false, false, false,
				false, true, false, false, false, false, false, false, false,
				false, false, true, false };
		boolean[] actuals = ABCCC.cycleAlgorithm(c, b, n);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test4() {
		int[] c = { 1, 2, 1, 5, 10 };
		int n = 40;
		int b = 7;
		boolean[] expecteds = { false, true, false, false, false, false, false,
				false, false, false, false, true, false, false, false, false,
				false, false, false, false, false, true, false, false, false,
				false, false, false, false, false, false, true, false, false,
				false, false, false, false, false, false };
		boolean[] actuals = ABCCC.cycleAlgorithm(c, b, n);
		Assert.assertArrayEquals(expecteds, actuals);
	}

}
