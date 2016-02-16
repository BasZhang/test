package zorg.game_test.support;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import zorg.game_test.support.PosMoveUtilz.Moving;

public class PosMoveUtilzTest {

	@Test
	public void testSteps() {
		Character[] eg = { 'c', 'd', null, 'a', 'b', 'l', 'e', 'f', 'g', null,
				'h', 'i', 'j', 'k', 'm' };
		int[] before = { 3, 4, 0, 1, 6, 7, 8, 10, 11, 12, 13, 5, 14 };
		int[] after = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };

		List<Moving> steps = PosMoveUtilz.getSwaps(before, after);

		PosMoveUtilz.swap(eg, steps);

		Character[] expected = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', null, null };
		Assert.assertArrayEquals(expected, eg);
	}
}
