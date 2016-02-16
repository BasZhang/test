package zorg.game_test.composite_all;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.iterators.ReverseListIterator;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.commons.lang3.tuple.Pair;

/**
 * 一个“将一些物品尽量升到最高级”的示例。
 * 
 * @author zhangbo
 *
 */
public class CompositeAll {

	/**
	 * 配置
	 */
	protected ConfManager confManager;

	public void setConfManager(ConfManager confManager) {
		this.confManager = confManager;
	}

	/**
	 * 找“将一些物品尽量升到最高级”的解决办法。
	 * 
	 * @param onHand
	 *            原料物品列表
	 * @return 需要移除的物品和需要新增的物品信息。
	 */
	public Pair<List<Itemlike>, List<ItemCreateInfo>> findSolution(List<Itemlike> onHand) {

		MultiValueMap<Integer, Object> bySn = MultiValueMap.multiValueMap(new HashMap<Integer, Object>(), ArrayList::new);
		onHand.forEach(item -> bySn.put(item.getUniq(), item));

		List<Itemlike> toRemove = new ArrayList<>();
		while (true) {
			boolean stillNeedCheck = false;
			for (Integer sn : new HashSet<>(bySn.keySet())) {
				Conf _conf = confManager.get(sn);
				if (!_conf.canComposite()) {
					bySn.remove(sn);
					continue;
				}
				int nextSn = _conf.getTargetUniq();
				int oneNeed = _conf.getNeeds();
				int has = bySn.size(sn);
				if (has > oneNeed) {
					int canProduce = has / oneNeed;
					ReverseListIterator<?> reverse_ite = new ReverseListIterator<>((List<?>) bySn.getCollection(sn));
					for (int i = canProduce * oneNeed; i > 0; i--) {
						Object object = reverse_ite.next();
						if (object instanceof Itemlike) {
							Itemlike item = (Itemlike) object;
							toRemove.add(item);
						}
						reverse_ite.remove();
					}
					bySn.putAll(nextSn, Collections.nCopies(canProduce, new ItemCreateInfo(nextSn, 1)));
					stillNeedCheck = true;
				}
			}
			if (!stillNeedCheck)
				break;
		}
		List<ItemCreateInfo> toGen = new ArrayList<>();
		bySn.forEach((sn, snValue) -> {
			List<?> objList = List.class.cast(snValue);
			long voNum = objList.stream().filter(obj -> obj instanceof ItemCreateInfo).count();
			if (voNum > 0) {
				toGen.add(new ItemCreateInfo(sn, (int) voNum));
			}
		});
		Pair<List<Itemlike>, List<ItemCreateInfo>> answer = Pair.of(toRemove, toGen);
		return answer;
	}
}
