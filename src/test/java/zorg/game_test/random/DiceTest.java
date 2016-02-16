package zorg.game_test.random;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Strings;

public class DiceTest {

	private Logger log = Logger.getGlobal();

	@Test
	public void testItem() {
		DiceBuilder<String> builder = DiceBuilder.defaultBuilder();
		Dice<String> dice = builder.addFace(null, 75).addFace("A", 20)
				.addFace("B", 5).create();
		for (int i = 80; i > 0; i--) {
			final String get = dice.toss();
			Assert.assertThat(get, new BaseMatcher<String>() {

				@Override
				public boolean matches(Object item) {
					return item == null || item.equals("A") || item.equals("B");
				}

				@Override
				public void describeTo(Description description) {
					description
							.appendText("result should be null, \"A\" or \"B\".");
				}
			});
		}
	}

	@Test
	@Ignore
	public void testCoverage() {
		DiceBuilder<Integer> builder = DiceBuilder.defaultBuilder();
		Dice<Integer> dice1 = builder.addFace(null, 90).addFace(1, 10).create();
		builder.reset();
		Dice<Integer> dice2 = builder.addFace(null, 95).addFace(2, 5).create();

		Map<Integer, Integer> stat = new HashMap<>();
		final int N = 10000000;
		final boolean[] b = new boolean[3];
		for (int c = N; c > 0; c--) {
			Arrays.fill(b, false);
			for (int timeKey = 1;; timeKey++) {
				Optional.ofNullable(dice1.toss()).ifPresent(
						index -> b[index] = true);
				Optional.ofNullable(dice2.toss()).ifPresent(
						index -> b[index] = true);
				if (b[1] & b[2]) {
					stat.merge(timeKey, 1, (x, y) -> x + y);
					break;
				}
			}
		}
		Integer totalTimes = stat.values().stream()
				.reduce(0, (v1, v2) -> v1 + v2);
		double exp = 0;
		for (Integer t : stat.keySet().stream().sorted()
				.toArray(Integer[]::new)) {
			Integer c = stat.get(t);
			double p = 1d * c / totalTimes;
			log.fine(String.format("第%1$03d次  %2$s %1$d:%3$d p=%4$1.6f%n", t,
					Strings.repeat("#", c / 50), c, p));
			exp += p * t;
		}
		log.info(String.format("%d内次数期望是%1.6f%n", N, exp));
	}
}
