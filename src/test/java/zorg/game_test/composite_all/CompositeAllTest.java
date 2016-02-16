package zorg.game_test.composite_all;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.tuple.Pair;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

public class CompositeAllTest {

	private Logger log = Logger.getGlobal();

	@Test
	public void testFindSolution() {

		ConfManager confMock = mockConfManager();

		List<Itemlike> gems = new ArrayList<>();
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(1));
		gems.add(new ItemDemo(2));
		gems.add(new ItemDemo(2));
		gems.add(new ItemDemo(2));
		gems.add(new ItemDemo(2));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(12));
		gems.add(new ItemDemo(14));

		CompositeAll logic = new CompositeAll();
		logic.setConfManager(confMock);

		long tt = System.currentTimeMillis();

		Pair<List<Itemlike>, List<ItemCreateInfo>> actual = logic
				.findSolution(gems);

		log.info("本次用时" + (System.currentTimeMillis() - tt) + "millis。");

		Itemlike[] toRemove = actual.getLeft().stream()
				.sorted((a, b) -> a.getUniq() - b.getUniq())
				.toArray(Itemlike[]::new);
		ItemCreateInfo[] toGen = actual.getRight().stream()
				.sorted((a, b) -> a.getUniq() - b.getUniq())
				.toArray(ItemCreateInfo[]::new);

		Itemlike[] expectedToRemove = {
				//
				new ItemDemo(1), //
				new ItemDemo(1), //
				new ItemDemo(1), //
				new ItemDemo(1), //
				new ItemDemo(2), //
				new ItemDemo(2), //
				new ItemDemo(2), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12), //
				new ItemDemo(12) //
		};
		ItemCreateInfo[] expectedToGen = {
				//
				new ItemCreateInfo(3, 1), //
				new ItemCreateInfo(13, 2) //
		};

		Assert.assertArrayEquals(expectedToRemove, toRemove);
		Assert.assertArrayEquals(expectedToGen, toGen);
	}

	private ConfManager mockConfManager() {
		Map<Integer, Conf> confMap = new HashMap<>();
		confMap.put(1, new ConfDemo(1, 2, 4));
		confMap.put(2, new ConfDemo(2, 3, 4));
		confMap.put(3, new ConfDemo(3, 4, 4));
		confMap.put(4, new ConfDemo(4, 0, 0));
		confMap.put(11, new ConfDemo(11, 12, 4));
		confMap.put(12, new ConfDemo(12, 13, 4));
		confMap.put(13, new ConfDemo(13, 14, 4));
		confMap.put(14, new ConfDemo(14, 0, 0));

		ConfManager confMock = EasyMock.createMock(ConfManager.class);
		for (int i : confMap.keySet()) {
			EasyMock.expect(confMock.get(i)).andReturn(confMap.get(i))
					.anyTimes();
		}
		EasyMock.replay(confMock);
		return confMock;
	}
}
