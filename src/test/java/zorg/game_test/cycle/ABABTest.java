package zorg.game_test.cycle;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ABABTest {

	@Test
	public void test1() {
		int[] c = { 1 };
		int b = 0;
		int n = 12;
		boolean[] expecteds = new boolean[n];
		Arrays.fill(expecteds, true);
		boolean[] actuals = ABAB.cycleAlgorithm(c, b, n);
		Assert.assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void test2() {
		int[] c = { 1, 2, 3 };
		int b = 2;
		int n = 12;
		boolean[] expecteds = { true, false, false, true, true, false, true,
				false, false, true, true, false };
		boolean[] actuals = ABAB.cycleAlgorithm(c, b, n);
		Assert.assertArrayEquals(expecteds, actuals);
	}

}
