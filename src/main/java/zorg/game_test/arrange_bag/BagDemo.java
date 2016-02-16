package zorg.game_test.arrange_bag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.collections4.list.GrowthList;

import zorg.game_test.support.PosMoveUtilz;
import zorg.game_test.support.PosMoveUtilz.Moving;

/**
 * 假设这是个可整理的背包。
 * 
 * @author 波
 *
 */
public class BagDemo implements Arrangable {

	List<Itemlike> items;

	@Override
	public List<Change> arrange() {
		// 归并
		Map<Object, List<Itemlike>> groups = group();
		// 同类压缩，记录移除
		List<Change> removals = compactAndNote(groups, x -> {
		});
		// 排序、拼接
		List<Itemlike> newBag = link(groups);
		// 记录交换
		List<Change> swaps = swapAndNote(newBag);
		// 集合替换
		this.items = newBag;

		List<Change> result = removals;
		result.addAll(swaps);

		return result;
	}

	private Map<Object, List<Itemlike>> group() {
		Map<Object, List<Itemlike>> groups = new HashMap<>();
		for (Itemlike item : items) {
			if (item != null && item.getCount() > 0) {
				int key = item.getUniq();
				if (groups.containsKey(key)) {
					groups.get(key).add(item);
				} else {
					List<Itemlike> group = new GrowthList<>();
					group.add(item);
					groups.put(key, group);
				}
			}
		}
		return groups;
	}

	private List<Change> compactAndNote(Map<Object, List<Itemlike>> groups,
			Consumer<Itemlike> removeFunction) {
		Map<Integer, Integer> changeMap = new HashMap<>();

		for (List<Itemlike> group : groups.values()) {
			for (int i = 0, j = group.size() - 1; i < j;) {
				Itemlike itemI = group.get(i);

				int cellCapI = itemI.getLimit() - itemI.getCount();
				if (cellCapI > 0) {
					Itemlike itemJ = group.get(j);
					int transCount = Math.min(cellCapI, itemJ.getCount());

					itemI.setCount(itemI.getCount() + transCount);
					itemJ.setCount(itemJ.getCount() - transCount);

					if (itemI.getCount() == itemI.getLimit()) {
						i++;
					}
					if (itemJ.getCount() == 0) {
						removeFunction.accept(itemJ);
						j--;
					}
					changeMap.put(itemI.getPosition(), itemI.getCount());
					changeMap.put(itemJ.getPosition(), itemJ.getCount());
				} else {
					i++;
				}
			}
		}
		List<Change> result = new ArrayList<>();
		changeMap.forEach((pos, count) -> result.add(Change.newCountChange(pos,
				count)));
		return result;
	}

	private List<Itemlike> link(Map<Object, List<Itemlike>> groups) {
		List<List<Itemlike>> keyGroup = new ArrayList<>(groups.values());
		keyGroup.sort((groupA, groupB) -> groupA.get(0)
				.compareTo(groupB.get(0)));
		List<Itemlike> result = new ArrayList<>();
		keyGroup.forEach((group) -> {
			group.forEach(item -> {
				if (item.getCount() > 0)
					result.add(item);
			});
		});
		return result;
	}

	private List<Change> swapAndNote(List<Itemlike> newBag) {

		int sz = newBag.size();
		int[] before = new int[sz];
		int[] after = new int[sz];
		for (int i = 0; i < sz; i++) {
			Itemlike item = newBag.get(i);
			before[i] = item.getPosition();
			item.setPosition(i);
			after[i] = i;
		}
		List<Moving> swaps = PosMoveUtilz.getSwaps(before, after);
		List<Change> result = new ArrayList<>();
		swaps.forEach((mov) -> {
			result.add(Change.newSwap(mov.crtPos, mov.finalPos));
		});
		return result;
	}

}
