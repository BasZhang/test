package zorg.game_test.arrange_bag;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Lists;

public class BagDemoTest {

	@Test
	@Ignore
	public void testArrange1() {
		BagDemo bag = new BagDemo();
		ItemDemo[] items = {
				new ItemDemo('c', 1, 1, 0),
				new ItemDemo('d', 1, 1, 1),
				null,
				new ItemDemo('a', 1, 1, 3),
				new ItemDemo('b', 1, 1, 4),
				new ItemDemo('l', 1, 1, 5),
				new ItemDemo('e', 1, 1, 6),
				new ItemDemo('f', 1, 1, 7),
				new ItemDemo('g', 1, 1, 8),
				null,
				new ItemDemo('h', 1, 1, 10),
				new ItemDemo('i', 1, 1, 11),
				new ItemDemo('j', 1, 1, 12),
				new ItemDemo('k', 1, 1, 13),
				new ItemDemo('m', 1, 1, 14),
		};
		bag.items = Lists.newArrayList(items);

		Matcher<List<Itemlike>> matcher = new BaseMatcher<List<Itemlike>>() {
			ItemDemo[] expected = {
					new ItemDemo('a', 1, 1, 0),
					new ItemDemo('b', 1, 1, 1),
					new ItemDemo('c', 1, 1, 2),
					new ItemDemo('d', 1, 1, 3),
					new ItemDemo('e', 1, 1, 4),
					new ItemDemo('f', 1, 1, 5),
					new ItemDemo('g', 1, 1, 6),
					new ItemDemo('h', 1, 1, 7),
					new ItemDemo('i', 1, 1, 8),
					new ItemDemo('j', 1, 1, 9),
					new ItemDemo('k', 1, 1, 10),
					new ItemDemo('l', 1, 1, 11),
					new ItemDemo('m', 1, 1, 12),
			};
			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				List<Itemlike> actual = (List<Itemlike>) item;
				
				if (expected.length != actual.size())
					return false;
				for (int i = 0; i < expected.length; i++) {
					if (!expected[i].equals(actual.get(i))) {
						return false;
					}
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendValue(expected);
			}
		};
		bag.arrange();
		
		assertThat(bag.items, matcher);
	}

	@Test
	public void testArrange2() {
		BagDemo bag = new BagDemo();
		ItemDemo[] items = {
				new ItemDemo('c', 99, 1, 0),
				new ItemDemo('c', 99, 1, 1),
				null,
				new ItemDemo('a', 1, 1, 3),
				new ItemDemo('b', 8, 8, 4),
				new ItemDemo('l', 1, 1, 5),
				new ItemDemo('b', 8, 2, 6),
				new ItemDemo('f', 8, 6, 7),
				new ItemDemo('b', 8, 1, 8),
				null,
				new ItemDemo('h', 1, 1, 10),
				new ItemDemo('i', 1, 1, 11),
				new ItemDemo('a', 1, 1, 12),
				new ItemDemo('f', 8, 8, 13),
				new ItemDemo('m', 1, 1, 14),
		};
		bag.items = Lists.newArrayList(items);

		Matcher<List<Itemlike>> matcher = new BaseMatcher<List<Itemlike>>() {
			ItemDemo[] expected = {
					new ItemDemo('a', 1, 1, 0),
					new ItemDemo('a', 1, 1, 1),
					new ItemDemo('b', 8, 8, 2),
					new ItemDemo('b', 8, 3, 3),
					new ItemDemo('c', 99, 2, 4),
					new ItemDemo('f', 8, 8, 5),
					new ItemDemo('f', 8, 6, 6),
					new ItemDemo('h', 1, 1, 7),
					new ItemDemo('i', 1, 1, 8),
					new ItemDemo('l', 1, 1, 9),
					new ItemDemo('m', 1, 1, 10),
			};
			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				List<Itemlike> actual = (List<Itemlike>) item;
				
				if (expected.length != actual.size())
					return false;
				for (int i = 0; i < expected.length; i++) {
					if (!expected[i].equals(actual.get(i))) {
						return false;
					}
				}
				return true;
			}

			@Override
			public void describeTo(Description description) {
				description.appendValue(expected);
			}
		};
		bag.arrange();
		
		assertThat(bag.items, matcher);
	}
	@Test
	public void testArrange3() {
		BagDemo bag = new BagDemo();
		ItemDemo[] items = {
				new ItemDemo('c', 99, 1, 0),
				new ItemDemo('c', 99, 1, 1),
				null,
				new ItemDemo('a', 1, 1, 3),
				new ItemDemo('b', 8, 8, 4),
				new ItemDemo('l', 1, 1, 5),
				new ItemDemo('b', 8, 2, 6),
				new ItemDemo('f', 8, 6, 7),
				new ItemDemo('b', 8, 1, 8),
				null,
				new ItemDemo('h', 1, 1, 10),
				new ItemDemo('i', 1, 1, 11),
				new ItemDemo('a', 1, 1, 12),
				new ItemDemo('f', 8, 8, 13),
				new ItemDemo('m', 1, 1, 14),
		};
		
		bag.items = Lists.newArrayList(items);
		List<Change> stps = bag.arrange();
		
		applyChanges(items, stps);
		
		ItemDemo[] expecteds = {
				new ItemDemo('a', 1, 1, 0),
				new ItemDemo('a', 1, 1, 1),
				new ItemDemo('b', 8, 8, 2),
				new ItemDemo('b', 8, 3, 3),
				new ItemDemo('c', 99, 2, 4),
				new ItemDemo('f', 8, 8, 5),
				new ItemDemo('f', 8, 6, 6),
				new ItemDemo('h', 1, 1, 7),
				new ItemDemo('i', 1, 1, 8),
				new ItemDemo('l', 1, 1, 9),
				new ItemDemo('m', 1, 1, 10),
				null, null, null, null,
		};
		assertArrayEquals(expecteds, items);
	}

	private void applyChanges(ItemDemo[] items, List<Change> stps) {
		for (Change stp : stps) {
			int type = stp.getType();
			int[] params = stp.getParams();
			if (type == Change.CHANGE_COUNT) {
				if (params[1] > 0)
					items[params[0]].setCount(params[1]);
				else
					items[params[0]] = null;
			}
			if (type == Change.CHANGE_SWAP) {
				ItemDemo tmp = items[params[0]];
				items[params[0]] = items[params[1]];
				items[params[1]] = tmp;
			}
		}
	}
}
