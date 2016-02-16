package zorg.game_test.random;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import zorg.game_test.support.MathUtilz;

class DiceBuilderImpl<F> implements DiceBuilder<F> {
	private Map<F, Integer> faces = new TreeMap<>();
	private int emptyWeight;

	DiceBuilderImpl() {
	}

	@Override
	public DiceBuilder<F> addFace(F face, int weight) {
		if (face != null) {
			this.faces.put(face, weight);
		} else {
			this.emptyWeight = weight;
		}
		return this;
	}

	@Override
	public DiceBuilder<F> removeFace(F face) {
		if (this.faces.containsKey(face)) {
			this.faces.remove(face);
		} else {
			this.emptyWeight = 0;
		}
		return this;
	}

	@Override
	public DiceBuilder<F> reset() {
		this.faces.clear();
		this.emptyWeight = 0;
		return this;
	}

	@Override
	public Dice<F> create() {
		Dice<F> dice = new FairDice<>(this.faces, this.emptyWeight);
		return dice;
	}

	/**
	 * 概率均匀分布的骰子。
	 * 
	 * @author zhangbo
	 *
	 * @param <S>
	 */
	private class FairDice<S> implements Dice<S> {
		private S[] dice;
		private Random ran = new Random();

		public FairDice(Map<S, Integer> faceWeight, int emptyWeight) {
			Set<Entry<S, Integer>> set = faceWeight.entrySet();
			int[] weights = new int[set.size() + Math.min(emptyWeight, 1)];
			@SuppressWarnings("unchecked")
			S[] s = (S[]) new Object[weights.length];
			Iterator<Entry<S, Integer>> setIter = set.iterator();
			for (int i = 0; i < weights.length; i++) {
				if (setIter.hasNext()) {
					Entry<S, Integer> entry = setIter.next();
					S f = entry.getKey();
					int weight = entry.getValue();
					s[i] = f;
					weights[i] = weight;
				} else {
					weights[i] = emptyWeight;
					break;
				}
			}
			MathUtilz.reduct(weights);
			List<S> simpleList = new ArrayList<>();
			for (int i = 0; i < s.length; i++) {
				for (int c = weights[i]; c > 0; c--) {
					simpleList.add(s[i]);
				}
			}
			this.dice = simpleList.toArray(s);
		}

		@Override
		public S toss() {
			return dice[ran.nextInt(dice.length)];
		}
	}

}
